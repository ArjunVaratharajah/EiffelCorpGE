package org.gustaveeiffel.fr.eiffelcorp.ifservice.server;

import java.rmi.RemoteException;
import java.util.List;

import org.gustaveeiffel.fr.eiffelcorp.common.database.ProductDatabaseUtil;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;

public class IfService {

	public String getProducts() throws RemoteException {
		List<IProduct> products = ProductDatabaseUtil.getAllAvailableAndSoldOnce();

		if (products.isEmpty()) {
			return "===List of products===\nNo product";
		}
		
		String info = "===List of products===\n";

		for (IProduct product : products) {
			info += product.getInfo() + "\n";
		}

		return info;
	}
}
