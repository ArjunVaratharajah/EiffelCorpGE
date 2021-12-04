package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.exception.CustomerNotFoundException;
import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.customer.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerDatabaseUtil {

    public static void dropTable() {
        try {
            Statement st = DatabaseUtil.getConnection().createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS customer");
            st.close();
            System.out.println("customer table has been deleted.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable() {
        try {
            Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
                    "CREATE TABLE  IF NOT EXISTS customer(",
                    "id SERIAL primary key,",
                    "firstname char(50) not null,",
                    "lastname char(50) not null,",
                    "budget numeric(10)",
                    ")"
            );

            st.executeUpdate(StringUtil.join(sql));
            st.close();
            System.out.println("customer table has been created.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Customer create(String firstname, String lastname, double budget) {
        try {
            Statement stmt = DatabaseUtil.getConnection().createStatement();

            String query = "INSERT INTO customer (firstname, lastname, budget) VALUES (?, ?, ?) RETURNING id";

            PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, firstname);
            preparedStmt.setString(2, lastname);
            preparedStmt.setDouble(3, budget);
            preparedStmt.execute();

            ResultSet rs = preparedStmt.getGeneratedKeys();
            rs.next();
            int customerId = rs.getInt(1);

            stmt.close();

            return new Customer(customerId, firstname, lastname, budget);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Customer getById(int id) {
        try {
            Statement stmt = DatabaseUtil.getConnection().createStatement();
            PreparedStatement ps = DatabaseUtil.getConnection().prepareStatement("SELECT * FROM customer where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            String firstname = rs.getString("firstname").trim();
            String lastname = rs.getString("lastname").trim();
            double budget = rs.getDouble("budget");

            stmt.close();
            return new Customer(id, firstname, lastname, budget);
        } catch (Exception e) {
            throw new CustomerNotFoundException(id);
        }
    }

    public static void update(Customer customer) {
        try {
            String query = "UPDATE customer SET firstname = ?, lastname = ?, budget = ? WHERE id = ?";

            PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
            preparedStmt.setString(1, customer.getFirstname());
            preparedStmt.setString(2, customer.getLastname());
            preparedStmt.setDouble(3, customer.getBudget());
            preparedStmt.setInt(4, customer.getId());
            preparedStmt.execute();
            preparedStmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Customer> getAll() {
        List<Customer> customers = new ArrayList<Customer>();

        try {
            Statement stmt = DatabaseUtil.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customer order by id");

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname").trim();
                String lastname = rs.getString("lastname").trim();
                double budget = rs.getDouble("budget");

                customers.add(new Customer(id, firstname, lastname, budget));
            }
            stmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return customers;
    }

}
