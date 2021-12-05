package org.gustaveeiffel.fr.eiffelcorp.ifservice.client;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService;
import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceServiceLocator;


public class ClientIfService {

	public static void main(String[] args) throws ServiceException, RemoteException {
		IfService service = new IfServiceServiceLocator().getIfService();
		try {
			String products = service.getProducts();
			System.out.println(products);
		} catch (RuntimeException e) {
			System.out.print(e.getMessage());
		}
	}
}
