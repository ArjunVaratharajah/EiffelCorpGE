package org.gustaveeiffel.fr.eiffelcorp.ifservice.bak;

import org.gustaveeiffel.fr.eiffelcorp.common.customer.CartProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.customer.Customer;
import org.gustaveeiffel.fr.eiffelcorp.common.database.CartDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.CustomerDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.EmployeeDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.database.ProductDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.*;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class IfService {

    public String getProducts() throws RemoteException {
        List<IProduct> products = ProductDatabaseUtil.getAllAvailableAndSoldOnce();

        if (products.isEmpty()) {
            return "No product.";
        }

        String info = "";

        for (IProduct product : products) {
            info += product.getInfo() + "\n";
        }

        return info;
    }

    public String getCustomerFullname(int customerId) throws RemoteException {
        return CustomerDatabaseUtil.getById(customerId).getFullname();
    }

    public String getCustomerInfo(int customerId) throws RemoteException {
        return CustomerDatabaseUtil.getById(customerId).getInfo();
    }

    public String getCart(int customerId) throws RemoteException {
        List<CartProduct> cart = CartDatabaseUtil.getByCustomerId(customerId);

        if (cart.isEmpty()) {
            return "No product in cart.";
        }

        String info = "";
        double total = 0;
        for (CartProduct cartProduct : cart) {
            IProduct product = ProductDatabaseUtil.getById(cartProduct.getProducId());

            info += product.getInfo() + "\n";
            total += product.getPrice();
        }

        info += "\nTotal: " + total;

        return info;
    }

    public String deleteProductFromCart(int productId, int customerId) throws RemoteException {
        try {
            CartProduct cartProduct = CartDatabaseUtil.getCartProduct(productId);
        } catch (CartProductNotFoundException e) {
            return e.getMessage();
        }

        CartDatabaseUtil.deleteProduct(productId, customerId);
        IProduct product = ProductDatabaseUtil.getById(productId);

        return "The product " + product.getName() + " has been deleted from the cart.";
    }

    public String addProductToCart(int productId, int customerId) throws RemoteException {
        IProduct product = null;
        try {
            product = ProductDatabaseUtil.getById(productId);
        } catch (ProductNotFoundException e) {
            return e.getMessage();
        }

        if (!product.isAvailable()) {
            return new ProductIsNotAvailableException().getMessage();
        }

        if (!product.hasAlreadyBeenSold()) {
            return new ProductHasNotAlreadyBeenSoldException().getMessage();
        }

        try {
            CartProduct cartProduct = CartDatabaseUtil.getCartProduct(productId);

            return new ProductIsInACartException().getMessage();
        } catch (CartProductNotFoundException e) {
            CartDatabaseUtil.addProduct(productId, customerId);

            return "The product " + product.getName() + " has been added to the cart.";
        }
    }

    public String payCart(int customerId) throws RemoteException {
        List<CartProduct> cart = CartDatabaseUtil.getByCustomerId(customerId);

        if (cart.isEmpty()) {
            return "No product in cart.";
        }

        double total = 0;
        List<IProduct> productsInCart = new ArrayList<>();
        for (CartProduct cartProduct : cart) {
            IProduct product = ProductDatabaseUtil.getById(cartProduct.getProducId());
            total += product.getPrice();
            productsInCart.add(product);
        }

        Customer customer = CustomerDatabaseUtil.getById(customerId);
        if (customer.getBudget() < total) {
            return new BudgetIsNotEnoughException(customer.getBudget(), total).getMessage();
        }

        // TODO: [BEGIN] move logic to Bank WebService
        for (IProduct product : productsInCart) {
            IEmployee employee = EmployeeDatabaseUtil.getById(product.getOwnerId());
            employee.addToBudget(product.getPrice());
            EmployeeDatabaseUtil.update(employee);
        }

        customer.removeFromBudget(total);
        CustomerDatabaseUtil.update(customer);

        CartDatabaseUtil.deleteProductsByCustomerId(customerId);
        for (IProduct productInCart : productsInCart) {
            ProductDatabaseUtil.deleteById(productInCart.getId());
        }
        // TODO: [END] move logic to Bank WebService

        String info = "Thanks for purchasing the following products:\n";
        for (IProduct product : productsInCart) {
            info += product.getInfo() + "\n";
        }

        return info;
    }

}
