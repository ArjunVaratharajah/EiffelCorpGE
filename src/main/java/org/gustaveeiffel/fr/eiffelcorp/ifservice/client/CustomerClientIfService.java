package org.gustaveeiffel.fr.eiffelcorp.ifservice.client;

import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfService;
import org.gustaveeiffel.fr.eiffelcorp.ifservice.common.IfServiceServiceLocator;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CustomerClientIfService {

    private int customerId;
    private IfService ifService;

    public CustomerClientIfService(int customerId) throws ServiceException {
        this.customerId = customerId;
        ifService = new IfServiceServiceLocator().getIfService();
    }

    public void execute() throws RemoteException {
        displayWelcome();

        do {
            System.out.println("\nWhat do you want to do ?");
            System.out.println("1. Display products");
            System.out.println("2. Display my cart");
            System.out.println("3. Add product to my cart");
            System.out.println("4. Delete product from my cart");
            System.out.println("5. Pay my cart");

            System.out.print("Select option: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        displayCart();
                        break;
                    case 3:
                        addProductToMyCart();
                        break;
                    case 4:
                        deleteProductFromMyCart();
                        break;
                    case 5:
                        payMyCart();
                        break;

                    default:
                        System.out.println("Invalid option chosen.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option format, it should be a number.");
            }
        } while (true);
    }

    private void displayWelcome() throws RemoteException {
        String fullname = ifService.getCustomer(customerId);
        System.out.println("===Welcome back to IfService " + fullname + "===");
    }

    private void displayProducts() throws RemoteException {
        System.out.println("\n===List of products===");
        System.out.println(ifService.getProducts());
    }

    private void displayCart() throws RemoteException {
        System.out.println("\n===My cart===");
        System.out.println(ifService.getCart(customerId));
    }

    private void addProductToMyCart() throws RemoteException {
        System.out.println("\n===Add product to my cart===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int idProduct = scanner.nextInt();

        String result = ifService.addProductToCart(idProduct, customerId);
        System.out.println(result);
    }

    private void deleteProductFromMyCart() throws RemoteException {
        System.out.println("\n===Delete product from my cart===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int idProduct = scanner.nextInt();

        String result = ifService.deleteProductFromCart(idProduct, customerId);
        System.out.println(result);
    }

    private void payMyCart() throws RemoteException {
        System.out.println("\n===Pay my cart===");
        String cartInfo = ifService.getCart(customerId);
        System.out.println(cartInfo);

        if (cartInfo.equals("No product in cart.")) {
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you confirm payment for your cart (y/n) ?");
        String choice = scanner.nextLine();

        if (choice.equals("y")) {
            String result = ifService.payCart(customerId);
            System.out.println(result);
        }
    }

}
