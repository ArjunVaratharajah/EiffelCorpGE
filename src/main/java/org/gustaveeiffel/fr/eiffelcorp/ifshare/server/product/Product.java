package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product;

import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.product.TypeProduct;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Product extends UnicastRemoteObject implements IProduct {

    private int id;
    private String name;
    private double price;
    private boolean isAvailable;
    private int ownerId;
    private boolean hasAlreadyBeenSold;
    private TypeProduct type;

    public Product(int id, String name, double price, boolean isAvailable, int ownerId, boolean hasAlreadyBeenSold, TypeProduct type) throws RemoteException {
        super();

        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
        this.ownerId = ownerId;
        this.hasAlreadyBeenSold = hasAlreadyBeenSold;
        this.type = type;
    }

    public Product() throws RemoteException {
        super();
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public double getPrice() throws RemoteException {
        return price;
    }

    @Override
    public boolean isAvailable() throws RemoteException {
        return isAvailable;
    }

    @Override
    public void setAvailable(boolean isAvailable) throws RemoteException {
        this.isAvailable = isAvailable;
    }

    @Override
    public void setPrice(double price) throws RemoteException {
        this.price = price;
    }


    @Override
    public int getOwnerId() throws RemoteException {
        return ownerId;
    }

    @Override
    public boolean hasAlreadyBeenSold() throws RemoteException {
        return hasAlreadyBeenSold;
    }

    @Override
    public void setHasAlreadyBeenSold(boolean hasAlreadyBeenSold) throws RemoteException {
        this.hasAlreadyBeenSold = hasAlreadyBeenSold;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "ID: " + id + " Type: " + type + " | Name: " + name + " | Price: " + price + " | Is available: " + isAvailable + " | OwnedBy: " + ownerId + " | Has Already Been Sold Once: " + hasAlreadyBeenSold;
    }

    @Override
    public void setOwnerId(int ownerId) throws RemoteException {
        this.ownerId = ownerId;
    }

    @Override
    public String getType() throws RemoteException {
        return type.name();
    }

}
