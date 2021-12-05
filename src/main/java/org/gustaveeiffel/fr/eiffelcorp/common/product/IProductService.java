package org.gustaveeiffel.fr.eiffelcorp.common.product;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IProductService extends Remote {

    List<IProduct> getAllAvailable() throws RemoteException;

    List<IProduct> getByOwnerId(int ownerId) throws RemoteException;

    IProduct create(String name, double price, int ownerId) throws RemoteException;

    String buy(int idProduct, int idBuyer) throws RemoteException;

    String putAsAvailable(int employeeId, int idProduct, double price) throws RemoteException;

    String review(int idProduct, int idEmployee, int rating, String comment) throws RemoteException;

    List<IReview> getReviews(int idProduct) throws RemoteException;

}
