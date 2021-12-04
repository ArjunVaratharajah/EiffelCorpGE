package org.gustaveeiffel.fr.eiffelcorp.common.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/eiffelcorp", "postgres",
						"root");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		return connection;
	}

}
