package org.gustaveeiffel.fr.eiffelcorp.common.product;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProduct extends Remote {

    int getId() throws RemoteException;

    String getName() throws RemoteException;

    double getPrice() throws RemoteException;

    boolean isAvailable() throws RemoteException;

    void setAvailable(boolean isAvailable) throws RemoteException;

    int getOwnerId() throws RemoteException;

    boolean hasAlreadyBeenSold() throws RemoteException;

    void setHasAlreadyBeenSold(boolean hasAlreadyBeenSold) throws RemoteException;

    String getInfo() throws RemoteException;

    void setOwnerId(int ownerId) throws RemoteException;
}
