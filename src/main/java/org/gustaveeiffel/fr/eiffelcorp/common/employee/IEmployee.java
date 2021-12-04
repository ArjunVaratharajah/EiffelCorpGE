package org.gustaveeiffel.fr.eiffelcorp.common.employee;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEmployee extends Remote {

    int getId() throws RemoteException;

    String getFirstname() throws RemoteException;

    String getLastname() throws RemoteException;

    String getFullname() throws RemoteException;

    double getBudget() throws RemoteException;

    String getInfo() throws RemoteException;

    void removeFromBudget(double price) throws RemoteException;

    void addToBudget(double price) throws RemoteException;
}
