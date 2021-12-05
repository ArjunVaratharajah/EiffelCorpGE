package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.employee;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Employee extends UnicastRemoteObject implements IEmployee {

    private int id;
    private String firstname;
    private String lastname;
    private double budget;

    public Employee(int id, String firstname, String lastname, double budget) throws RemoteException {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.budget = budget;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public String getFirstname() throws RemoteException {
        return firstname;
    }

    @Override
    public String getLastname() throws RemoteException {
        return lastname;
    }

    @Override
    public String getFullname() throws RemoteException {
        return firstname + " " + lastname;
    }

    @Override
    public double getBudget() throws RemoteException {
        return budget;
    }

    @Override
    public String getInfo() throws RemoteException {
        return "ID: " + id + " | Firstname: " + getFirstname() + " | Lastname: " + getLastname() + " | Budget: "
                + getBudget();
    }

    @Override
    public void removeFromBudget(double price) throws RemoteException {
        budget -= price;
    }

    @Override
    public void addToBudget(double price) throws RemoteException {
        budget += price;
    }

}
