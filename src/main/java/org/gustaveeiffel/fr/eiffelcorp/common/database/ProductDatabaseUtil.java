package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.exception.ProductNotFoundException;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.product.TypeProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;
import org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDatabaseUtil {

	public static void dropTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS product");
			st.close();
			System.out.println("product table has been deleted.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
				"CREATE TABLE IF NOT EXISTS product(",
					"id SERIAL primary key,",
					"name char(255) not null,",
					"price numeric(255) not null,",
					"ownerId integer not null,",
					"isAvailable boolean not null default 'true',",
					"hasAlreadyBeenSold boolean not null default 'false',",
					"type char(255) not null,",
					"CONSTRAINT fk_id_employee FOREIGN KEY(ownerId) REFERENCES employee(id)",
				")"
			);

			st.executeUpdate(StringUtil.join(sql));
			st.close();
			System.out.println("product table has been created.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static IProduct create(String name, double price, int ownerId, boolean isAvailable, TypeProduct type) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "INSERT INTO product (name, price, ownerId, isAvailable, hasAlreadyBeenSold, type) VALUES (?, ?, ?, ?, ?, ?)";

			boolean hasAlreadyBeenSold = false;

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			preparedStmt.setString(1, name);
			preparedStmt.setDouble(2, price);
			preparedStmt.setInt(3, ownerId);
			preparedStmt.setBoolean(4, isAvailable);
			preparedStmt.setBoolean(5, hasAlreadyBeenSold);
			preparedStmt.setString(6, type.name());
			preparedStmt.execute();
			
	    	ResultSet rs = preparedStmt.getGeneratedKeys();
	       	rs.next();
            int productId = rs.getInt(1);
			stmt.close();

			return new Product(productId, name, price, isAvailable, ownerId, hasAlreadyBeenSold, type);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void update(IProduct product) {
		try {
			String query = "UPDATE product SET name = ?, price = ?, ownerId = ?, isAvailable = ?, hasAlreadyBeenSold = ?, type = ? WHERE id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setString(1, product.getName());
			preparedStmt.setDouble(2, product.getPrice());
			preparedStmt.setInt(3, product.getOwnerId());
			preparedStmt.setBoolean(4, product.isAvailable());
			preparedStmt.setBoolean(5, product.hasAlreadyBeenSold());
			preparedStmt.setString(6, product.getType());
			preparedStmt.setInt(7, product.getId());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static IProduct getById(int id) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			PreparedStatement ps = DatabaseUtil.getConnection().prepareStatement("SELECT * FROM product where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			rs.next();
			String name = rs.getString("name").trim();
			double price = rs.getDouble("price");
			boolean isAvailable = rs.getBoolean("isAvailable");
			int ownerId = rs.getInt("ownerId");
			boolean hasAlreadyBeenSold = rs.getBoolean("hasAlreadyBeenSold");
			String type = rs.getString("type").trim();

			stmt.close();
			return new Product(id, name, price, isAvailable, ownerId, hasAlreadyBeenSold, TypeProduct.valueOf(type));
		} catch (Exception e) {
			throw new ProductNotFoundException(id);
		}
	}

	public static List<IProduct> getAllAvailable() {
		List<IProduct> products = new ArrayList<>();

		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE isAvailable = true ORDER BY ID");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name").trim();
				double price = rs.getDouble("price");
				boolean isAvailable = rs.getBoolean("isAvailable");
				int ownerId = rs.getInt("ownerId");
				boolean hasAlreadyBeenSold = rs.getBoolean("hasAlreadyBeenSold");
				String type = rs.getString("type").trim();

				products.add(new Product(id, name, price, isAvailable, ownerId, hasAlreadyBeenSold, TypeProduct.valueOf(type)));
			}
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return products;
	}

	public static List<IProduct> getAllAvailableAndSoldOnce() {
		List<IProduct> products = new ArrayList<>();

		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM product WHERE isAvailable = true AND hasAlreadyBeenSold = true ORDER BY ID");

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name").trim();
				double price = rs.getDouble("price");
				boolean isAvailable = rs.getBoolean("isAvailable");
				int ownerId = rs.getInt("ownerId");
				boolean hasAlreadyBeenSold = rs.getBoolean("hasAlreadyBeenSold");
				String type = rs.getString("type").trim();

				products.add(new Product(id, name, price, isAvailable, ownerId, hasAlreadyBeenSold, TypeProduct.valueOf(type)));
			}
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return products;
	}

	public static List<IProduct> getByOwnerId(int ownerId) {
		List<IProduct> products = new ArrayList<>();

		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "SELECT * FROM product WHERE ownerId = ? ORDER BY ID";
			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, ownerId);
			ResultSet rs = preparedStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name").trim();
				double price = rs.getDouble("price");
				boolean isAvailable = rs.getBoolean("isAvailable");
				boolean hasAlreadyBeenSold = rs.getBoolean("hasAlreadyBeenSold");
				String type = rs.getString("type").trim();

				products.add(new Product(id, name, price, isAvailable, ownerId, hasAlreadyBeenSold, TypeProduct.valueOf(type)));
			}
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return products;
	}

	public static void deleteById(int productId) {
		try {
			Statement stmt = DatabaseUtil.getConnection().createStatement();
			String query = "DELETE FROM product WHERE id = ?";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, productId);
			preparedStmt.execute();
			stmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
