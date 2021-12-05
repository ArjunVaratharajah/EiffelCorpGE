package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.employee;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployeeService;

public class EmployeeServerIfShare {

    public static void main(String args[]) throws Exception {
        LocateRegistry.createRegistry(1100);
        IEmployeeService employeeService = new EmployeeService();
        Naming.rebind("rmi://localhost:1100/EmployeeService", employeeService);
        System.out.println("Product Server has started on port 1100.");
    }

}
