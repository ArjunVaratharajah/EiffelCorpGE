package org.gustaveeiffel.fr.eiffelcorp.common.database;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;

public class ReviewDatabaseUtil {
	
	public static void dropTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS review");
			st.close();
			System.out.println("review table has been deleted.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
				"CREATE TABLE IF NOT EXISTS review(",
					"id SERIAL primary key,",
					"employee_id integer not null,",
					"product_id integer not null,",
					"rating integer not null,",
					"comment char(255),",
					"date_time timestamp NOT NULL,",
					"CONSTRAINT fk_id_employee FOREIGN KEY(employee_id) REFERENCES employee(id),",
					"CONSTRAINT fk_id_product FOREIGN KEY(product_id) REFERENCES product(id),",
					"CONSTRAINT unique_employee_id_product_id UNIQUE(employee_id, product_id)",
				")"
			);

			st.executeUpdate(StringUtil.join(sql));
			st.close();
			System.out.println("review table has been created.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void addOrUpdate(int idProduct, int idEmployee, int rating, String comment) {
		try {
			String query = "INSERT INTO review (employee_id, product_id, rating, comment, date_time) VALUES (?, ?, ?, ?, ?) ON CONFLICT ON CONSTRAINT unique_employee_id_product_id DO UPDATE SET rating = EXCLUDED.rating, comment = EXCLUDED.comment, date_time = EXCLUDED.date_time";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, idEmployee);
			preparedStmt.setInt(2, idProduct);
			preparedStmt.setInt(3, rating);
			preparedStmt.setString(4, comment);
			preparedStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			preparedStmt.execute();
			preparedStmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
