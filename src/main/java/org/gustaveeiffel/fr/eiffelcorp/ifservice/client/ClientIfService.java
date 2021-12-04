package org.gustaveeiffel.fr.eiffelcorp.ifservice.client;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfService;
import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.product.ProductIfServiceServiceLocator;

public class ClientIfService {

	public static void main(String[] args) throws ServiceException, RemoteException {
		ProductIfService service = new ProductIfServiceServiceLocator().getProductIfService();
		String products = service.getProducts();

		System.out.println(products);
	}
}
