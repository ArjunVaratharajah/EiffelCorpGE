package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product;

import org.gustaveeiffel.fr.eiffelcorp.common.product.IReview;
import org.gustaveeiffel.fr.eiffelcorp.ifshare.server.employee.Employee;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Timestamp;

public class Review extends UnicastRemoteObject implements IReview {

    private int id;
    private final String productName;
    private final String employeeFullname;
    private final double rating;
    private final String comment;
    private Timestamp dateTime;

    public Review(int id, String productName, String employeeFullname, double rating, String comment, Timestamp dateTime) throws RemoteException {
        super();

        this.id = id;
        this.productName = productName;
        this.employeeFullname = employeeFullname;
        this.rating = rating;
        this.comment = comment;
        this.dateTime = dateTime;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "Date: " + dateTime + " Product: " + productName + " | Employee: " + employeeFullname + " | Rating: " + rating + " | Comment: " + comment;
    }
}
