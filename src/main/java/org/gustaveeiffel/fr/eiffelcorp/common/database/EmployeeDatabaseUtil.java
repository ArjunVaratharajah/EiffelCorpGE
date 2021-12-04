package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.EmployeeNotFoundException;
import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;
import org.gustaveeiffel.fr.eiffelcorp.ifshare.server.employee.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDatabaseUtil {

	public static void dropTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS employee");
			st.close();
			System.out.println("employee table has been deleted.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
				"CREATE TABLE  IF NOT EXISTS employee(",
					"id SERIAL primary key,",
					"firstname char(50) not null,",
					"lastname char(50) not null,",
					"budget numeric(10)",
				")"
			);

			st.executeUpdate(StringUtil.join(sql));
			st.close();
			System.out.println("employee table has been created.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static IEmployee create(String firstname, String lastname, double budget) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();

			String query = "INSERT INTO employee (firstname, lastname, budget) VALUES (?, ?, ?) RETURNING id";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, firstname);
			preparedStmt.setString(2, lastname);
			preparedStmt.setDouble(3, budget);
			preparedStmt.execute();

	       	ResultSet rs = preparedStmt.getGeneratedKeys();
	       	rs.next();
            int employeeId = rs.getInt(1);

			stmt.close();

			return new Employee(employeeId, firstname, lastname, budget);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static IEmployee getById(int id) throws EmployeeNotFoundException {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			PreparedStatement ps = DatabaseUtil.getConnection().prepareStatement("SELECT * FROM employee where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			String firstname = rs.getString("firstname").trim();
			String lastname = rs.getString("lastname").trim();
			double budget = rs.getDouble("budget");

			stmt.close();
			return new Employee(id, firstname, lastname, budget);
		} catch (Exception e) {
			throw new EmployeeNotFoundException(id);
		}
	}

	public static void update(IEmployee employee) {
		try {
			String query = "UPDATE employee SET firstname = ?, lastname = ?, budget = ? WHERE id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setString(1, employee.getFirstname());
			preparedStmt.setString(2, employee.getLastname());
			preparedStmt.setDouble(3, employee.getBudget());
			preparedStmt.setInt(4, employee.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<IEmployee> getAll() {
		List<IEmployee> employees = new ArrayList<IEmployee>();

		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employee order by id");

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname").trim();
				String lastname = rs.getString("lastname").trim();
				double budget = rs.getDouble("budget");

				employees.add(new Employee(id, firstname, lastname, budget));
			}
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return employees;
	}

}
