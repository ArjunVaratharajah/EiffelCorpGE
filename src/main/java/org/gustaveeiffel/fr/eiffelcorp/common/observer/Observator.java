package org.gustaveeiffel.fr.eiffelcorp.common.observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Observator extends UnicastRemoteObject implements IObservator {

    public Observator() throws RemoteException {
        super();
    }

    @Override
    public void notifyProductAvailable(String productInfo) throws RemoteException {
        System.out.println("\nNew product available or price updated: ");
        System.out.println(productInfo + "\n");
    }
}
