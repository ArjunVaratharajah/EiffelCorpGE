package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.customer.CartProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.exception.CartProductNotFoundException;
import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartDatabaseUtil {

	public static void dropTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS cart");
			st.close();
			System.out.println("cart table has been deleted.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
				"CREATE TABLE IF NOT EXISTS cart(",
					"id SERIAL primary key,",
					"product_id integer not null,",
					"customer_id  integer not null,",
					"CONSTRAINT fk_id_product FOREIGN KEY(product_id) REFERENCES product(id),",
					"CONSTRAINT fk_id_customer FOREIGN KEY(customer_id) REFERENCES customer(id),",
					"CONSTRAINT unique_product_id_customer_id UNIQUE(product_id, customer_id)",
				")"
			);

			st.executeUpdate(StringUtil.join(sql));
			st.close();
			System.out.println("cart table has been created.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static CartProduct getCartProduct(int productId) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "SELECT * from cart WHERE product_id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, productId);
			preparedStmt.execute();

			ResultSet rs = preparedStmt.executeQuery();
			rs.next();
			int id = rs.getInt("id");
			int customerId = rs.getInt("customer_id");
			stmt.close();

			return new CartProduct(id, productId, customerId);
		} catch (Exception e) {
			System.out.println(e);
			throw new CartProductNotFoundException(productId);
		}
	}

	public static void addProduct(int productId, int customerId) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "INSERT INTO cart (product_id, customer_id) VALUES (?, ?)";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, productId);
			preparedStmt.setInt(2, customerId);
			preparedStmt.execute();

			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void deleteProduct(int productId, int customerId) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "DELETE FROM cart where product_id = ? AND customer_id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, productId);
			preparedStmt.setInt(2, customerId);
			preparedStmt.execute();

			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static List<CartProduct> getByCustomerId(int customerId) {
		List<CartProduct> cart = new ArrayList<>();

		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "SELECT * FROM cart where customer_id = ?";
			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, customerId);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				int productId = rs.getInt("product_id");

				cart.add(new CartProduct(id, productId, customerId));
			}
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return cart;
	}

	public static void deleteProductsByCustomerId(int customerId) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "DELETE FROM cart WHERE customer_id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, customerId);
			preparedStmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
