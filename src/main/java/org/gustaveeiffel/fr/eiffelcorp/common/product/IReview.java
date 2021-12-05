package org.gustaveeiffel.fr.eiffelcorp.common.product;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IReview extends Remote {

    String getInfo() throws RemoteException;
}
