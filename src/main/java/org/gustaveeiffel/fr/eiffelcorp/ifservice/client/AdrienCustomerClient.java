package org.gustaveeiffel.fr.eiffelcorp.ifservice.client;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;


public class AdrienCustomerClient {

    public static void main(String[] args) throws ServiceException, RemoteException {
        // ID 1 = Adrien
        new CustomerClientIfService(1).execute();
    }

}
