package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import org.gustaveeiffel.fr.eiffelcorp.common.customer.CartProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.database.CartDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.EmployeeDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.ProductDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.ReviewDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.BudgetIsNotEnoughException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.BuyerSameAsSellerException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.CartProductNotFoundException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.InvalidProductRatingException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.NotProductOwnerException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductHasNotAlreadyBeenSoldException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductIsInACartException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductIsNotAvailableException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductNameInvalidException;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductPriceInvalidException;
import org.gustaveeiffel.fr.eiffelcorp.common.observer.IObservator;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProductService;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IReview;
import org.gustaveeiffel.fr.eiffelcorp.common.product.TypeProduct;

public class ProductService extends UnicastRemoteObject implements IProductService {

	private Map<TypeProduct, Set<IObservator>> employeesToNotifyByProductType;

	public ProductService() throws RemoteException {
		super();
		employeesToNotifyByProductType = new HashMap<>();
		employeesToNotifyByProductType.put(TypeProduct.clothes, new HashSet<IObservator>());
		employeesToNotifyByProductType.put(TypeProduct.tech, new HashSet<IObservator>());
		employeesToNotifyByProductType.put(TypeProduct.food, new HashSet<IObservator>());
	}

	@Override
	public IProduct create(String name, double price, int ownerId, String typeProduct) throws RemoteException {
		if (name.trim().isEmpty()) {
			throw new ProductNameInvalidException(name);
		}

		if (price <= 0) {
			throw new ProductPriceInvalidException(price);
		}

		return ProductDatabaseUtil.create(name, price, ownerId, false, TypeProduct.valueOf(typeProduct));
	}

	@Override
	public List<IProduct> getAllAvailable() throws RemoteException {
		return ProductDatabaseUtil.getAllAvailable();
	}

	@Override
	public List<IProduct> getByOwnerId(int ownerId) throws RemoteException {
		return ProductDatabaseUtil.getByOwnerId(ownerId);
	}

	@Override
	public String buy(int idProduct, int idBuyer) throws RemoteException {
		IProduct product = ProductDatabaseUtil.getById(idProduct);
		IEmployee buyer = EmployeeDatabaseUtil.getById(idBuyer);

		// The seller can't be the buyer
		if (idBuyer == product.getOwnerId()) {
			throw new BuyerSameAsSellerException();
		}

		if (!product.isAvailable()) {
			throw new ProductIsNotAvailableException();
		}

		if (buyer.getBudget() < product.getPrice()) {
			throw new BudgetIsNotEnoughException(buyer.getBudget(), product.getPrice());
		}

		try {
			CartProduct cartProduct = CartDatabaseUtil.getCartProduct(idProduct);
			throw new ProductIsInACartException();
		} catch (CartProductNotFoundException e) {
			// Product is not in a cart, we can continue
		}

		IEmployee seller = EmployeeDatabaseUtil.getById(product.getOwnerId());
		buyer.removeFromBudget(product.getPrice());
		seller.addToBudget(product.getPrice());

		product.setAvailable(false);
		product.setOwnerId(idBuyer);
		product.setHasAlreadyBeenSold(true);

		ProductDatabaseUtil.update(product);
		EmployeeDatabaseUtil.update(buyer);
		EmployeeDatabaseUtil.update(seller);

		return "The product " + product.getName() + " has correctly been sold by " + seller.getFullname() + " to "
				+ buyer.getFullname() + ".\nThe amount of the transaction was " + product.getPrice() + ".";
	}

	@Override
	public String putAsAvailable(int employeeId, int idProduct, double price) throws RemoteException {
		IProduct product = ProductDatabaseUtil.getById(idProduct);

		if (product.getOwnerId() != employeeId) {
			IEmployee employee = EmployeeDatabaseUtil.getById(product.getOwnerId());
			throw new NotProductOwnerException(product.getName(), employee.getFullname());
		}

		if (price <= 0) {
			throw new ProductPriceInvalidException(price);
		}

		product.setAvailable(true);
		product.setPrice(price);
		ProductDatabaseUtil.update(product);

		Set<IObservator> employeesToNotify = employeesToNotifyByProductType.get(TypeProduct.valueOf(product.getType()));
		for (IObservator employeeToNotify : employeesToNotify) {
			employeeToNotify.notifyProductAvailable(product.getInfo());
		}

		return "The product " + product.getName() + " has correctly been set to available with new price " + price
				+ ".";
	}

	@Override
	public String review(int idProduct, int idEmployee, int rating, String comment) throws RemoteException {
		IProduct product = ProductDatabaseUtil.getById(idProduct);

		if (product.getOwnerId() != idEmployee) {
			IEmployee employee = EmployeeDatabaseUtil.getById(product.getOwnerId());
			throw new NotProductOwnerException(product.getName(), employee.getFullname());
		}

		if (!product.hasAlreadyBeenSold()) {
			throw new ProductHasNotAlreadyBeenSoldException();
		}

		if (!product.isAvailable()) {
			throw new ProductIsNotAvailableException();
		}

		if (rating < 1 || rating > 5) {
			throw new InvalidProductRatingException();
		}

		ReviewDatabaseUtil.addOrUpdate(idProduct, idEmployee, rating, comment);

		return "The review has been added/updated for the product " + product.getName() + ".";
	}

	@Override
	public List<IReview> getReviews(int idProduct) throws RemoteException {
		// Throw an exception if the product does not exist for the client side
		ProductDatabaseUtil.getById(idProduct);

		return ReviewDatabaseUtil.getByProductId(idProduct);
	}

	@Override
	public synchronized void subscribe(IObservator clientEmployee, String typeProduct) throws RemoteException {
		employeesToNotifyByProductType.get(TypeProduct.valueOf(typeProduct)).add(clientEmployee);
	}

}
