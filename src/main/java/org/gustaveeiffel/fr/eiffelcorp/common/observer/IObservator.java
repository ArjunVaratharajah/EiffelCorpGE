package org.gustaveeiffel.fr.eiffelcorp.common.observer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObservator extends Remote {

    void notifyProductAvailable(int value) throws RemoteException;

}
