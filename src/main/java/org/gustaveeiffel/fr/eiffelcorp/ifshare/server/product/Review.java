package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product;

import org.gustaveeiffel.fr.eiffelcorp.common.product.IReview;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Review extends UnicastRemoteObject implements IReview {

    private int id;
    private final String productName;
    private final String employeeFullname;
    private final double rating;
    private final String comment;

    public Review(int id, String productName, String employeeFullname, double rating, String comment) throws RemoteException {
        super();

        this.id = id;
        this.productName = productName;
        this.employeeFullname = employeeFullname;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "Product: " + productName + " | Employee: " + employeeFullname + " | Rating: " + rating + " | Comment: " + comment;
    }
}
