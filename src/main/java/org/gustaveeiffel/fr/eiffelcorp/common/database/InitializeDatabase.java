package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;

import java.rmi.RemoteException;
import java.sql.SQLException;


public class InitializeDatabase {

    public static void main(String args[]) throws RemoteException, SQLException {
        ReviewDatabaseUtil.dropTable();
        TransactionDatabaseUtil.dropTable();
        ProductDatabaseUtil.dropTable();
        EmployeeDatabaseUtil.dropTable();
        CustomerDatabaseUtil.dropTable();

        System.out.println();

        CustomerDatabaseUtil.createTable();
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

        CustomerDatabaseUtil.create("Adrien", "KUMBO", 600); // ID 1
        CustomerDatabaseUtil.create("Thomas", "LEPARMENTIER", 20); // ID 2
        CustomerDatabaseUtil.create("Jean", "PINARD", 200); // ID 4
        CustomerDatabaseUtil.create("Bruce", "LUTOIS", 10); // ID 3

        System.out.print("\nDatabase has been initialized.");
    }
}
