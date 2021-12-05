package org.gustaveeiffel.fr.eiffelcorp.ifservice.client;

import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService;
import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;


public class CustomerClientIfService {

    private int customerId;
    private IfService ifService;

    public CustomerClientIfService(int customerId) throws ServiceException {
        this.customerId = customerId;
        ifService = new IfServiceServiceLocator().getIfService();
    }

    public void execute() throws RemoteException {
        displayWelcome();
    }

    private void displayWelcome() throws RemoteException {
        String fullname = ifService.getCustomer(customerId);
        System.out.println("===Welcome back to IfService " + fullname + "===");
    }
}
