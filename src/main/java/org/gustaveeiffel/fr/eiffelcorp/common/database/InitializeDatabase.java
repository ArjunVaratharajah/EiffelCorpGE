package org.gustaveeiffel.fr.eiffelcorp.common.database;

import java.rmi.RemoteException;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;



public class InitializeDatabase {

    public static void main(String args[]) throws RemoteException {
        ReviewDatabaseUtil.dropTable();
        TransactionDatabaseUtil.dropTable();
        ProductDatabaseUtil.dropTable();
        EmployeeDatabaseUtil.dropTable();

        EmployeeDatabaseUtil.createTable();
        ProductDatabaseUtil.createTable();
        TransactionDatabaseUtil.createTable();
        ReviewDatabaseUtil.createTable();

        IEmployee arjun = EmployeeDatabaseUtil.create("Arjun", "VARATHARAJAH", 100); // ID 1
        IEmployee benjamin = EmployeeDatabaseUtil.create("Benjamin", "JEDROCHA", 500); // ID 2
        IEmployee houcem = EmployeeDatabaseUtil.create("Houcem", "BOUBAKRI", 300); // ID 3
        IEmployee idriss = EmployeeDatabaseUtil.create("Idriss", "BENHAMED", 200); // ID 4

        ProductDatabaseUtil.create("Trousers", 5, arjun.getId(), true); // ID 1
        ProductDatabaseUtil.create("Smartphone", 80, arjun.getId(), false); // ID 2
        ProductDatabaseUtil.create("iPhone", 100, benjamin.getId(), false); // ID 3
        ProductDatabaseUtil.create("PC", 500, houcem.getId(), true); // ID 4

        System.out.print("Database has been initialized.");
    }
}
