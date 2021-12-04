package org.gustaveeiffel.fr.eiffelcorp.common.database;

import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.util.StringUtil;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class TransactionDatabaseUtil {

	public static void dropTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS transaction");
			st.close();
			System.out.println("transaction table has been deleted.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void createTable() {
		try {
			Statement st = DatabaseUtil.getConnection().createStatement();

            List<String> sql = Arrays.asList(
				"CREATE TABLE IF NOT EXISTS transaction(",
					"id SERIAL primary key,",
					"buyer_id integer not null,",
					"seller_id integer not null,",
					"product_id integer not null,",
					"amount numeric not null,",
					"CONSTRAINT fk_id_buyer FOREIGN KEY(buyer_id) REFERENCES employee(id),",
					"CONSTRAINT fk_id_seller FOREIGN KEY(seller_id) REFERENCES employee(id),",
					"CONSTRAINT fk_id_product FOREIGN KEY(product_id) REFERENCES product(id)",
				")"
			);

			st.executeUpdate(StringUtil.join(sql));
			st.close();
			System.out.println("transaction table has been created.");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void add(int idBuyer, int idSeller, int idProduct) {
		try {
			IProduct product = ProductDatabaseUtil.getById(idProduct);
			String query = "INSERT INTO transaction (buyer_id, seller_id, product_id, amount) VALUES (?, ?, ?, ?)";

			PreparedStatement preparedStmt = DatabaseUtil.getConnection().prepareStatement(query);
			preparedStmt.setInt(1, idBuyer);
			preparedStmt.setInt(2, idSeller);
			preparedStmt.setInt(3, product.getId());
			preparedStmt.setDouble(4, product.getPrice());
			preparedStmt.execute();
			preparedStmt.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
