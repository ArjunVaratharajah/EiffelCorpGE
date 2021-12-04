package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.employee;

import org.gustaveeiffel.fr.eiffelcorp.common.database.EmployeeDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;
import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployeeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class EmployeeService extends UnicastRemoteObject implements IEmployeeService {

	public EmployeeService() throws RemoteException {
		super();
	}

	@Override
	public List<IEmployee> getAll() throws RemoteException {
		return EmployeeDatabaseUtil.getAll(); 
	}

	@Override
	public IEmployee getById(int employeeId) throws RemoteException {
		return EmployeeDatabaseUtil.getById(employeeId);
	}

}
