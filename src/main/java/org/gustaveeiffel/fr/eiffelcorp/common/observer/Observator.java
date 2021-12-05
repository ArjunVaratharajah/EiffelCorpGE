package org.gustaveeiffel.fr.eiffelcorp.common.observer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Observator extends UnicastRemoteObject implements IObservator {

    public Observator() throws RemoteException {
        super();
    }

    @Override
    public void notifyProductAvailable(int value) throws RemoteException {
        System.out.println("New value : " + value);
    }
}
