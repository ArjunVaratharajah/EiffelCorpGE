package org.gustaveeiffel.fr.eiffelcorp.common.employee;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEmployeeService extends Remote {

    List<IEmployee> getAll() throws RemoteException;

    IEmployee getById(int employeeId) throws RemoteException;
}
