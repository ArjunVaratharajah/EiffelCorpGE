package org.gustaveeiffel.fr.eiffelcorp.ifshare.client;

import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployee;
import org.gustaveeiffel.fr.eiffelcorp.common.employee.IEmployeeService;
import org.gustaveeiffel.fr.eiffelcorp.common.observer.IObservator;
import org.gustaveeiffel.fr.eiffelcorp.common.observer.Observator;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProduct;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IProductService;
import org.gustaveeiffel.fr.eiffelcorp.common.product.IReview;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeClientIfShare {

    private int employeeId;
    private IObservator observator;

    private IProductService productService;
    private IEmployeeService employeeService;

    public EmployeeClientIfShare(int employeeId) throws MalformedURLException, NotBoundException, RemoteException {
        this.employeeId = employeeId;
        observator = new Observator();

        productService = (IProductService) Naming.lookup("rmi://localhost:1099/ProductService");
        employeeService = (IEmployeeService) Naming.lookup("rmi://localhost:1100/EmployeeService");
    }

    public void execute() throws Exception {
        displayWelcome();

        do {
            System.out.println("\nWhat do you want to do ?");
            System.out.println("1. Display employees");
            System.out.println("2. Display available products");
            System.out.println("3. Display my products");
            System.out.println("4. Display my info");
            System.out.println("5. Buy product");
            System.out.println("6. Put product as available with price");
            System.out.println("7. Review product");
            System.out.println("8. Display reviews of a product");
            System.out.println("9. Add new product");
            System.out.println("10. Subscribe to product type");

            System.out.print("Select option: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        displayEmployees();
                        break;
                    case 2:
                        displayProducts();
                        break;
                    case 3:
                        displayMyProducts();
                        break;
                    case 4:
                        displayMyInfo();
                        break;
                    case 5:
                        buyProduct();
                        break;
                    case 6:
                        putProductAsAvailableWithPrice();
                        break;
                    case 7:
                        reviewProduct();
                        break;
                    case 8:
                        displayReviewsOfAProduct();
                        break;
                    case 9:
                        addNewProduct();
                        break;
                    case 10:
                        subscribeToProductType();
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
        IEmployee employee = employeeService.getById(employeeId);
        System.out.println("===Welcome back to IfShare " + employee.getFullname() + "===");
    }

    private void displayMyInfo() throws RemoteException {
        System.out.println("\n===My info===");
        IEmployee employee = employeeService.getById(employeeId);
        System.out.println(employee.getInfo());
    }

    private void displayEmployees() throws RemoteException {
        List<IEmployee> employees = employeeService.getAll();
        System.out.println("\n===List of employees===");
        for (IEmployee employee : employees) {
            System.out.println(employee.getInfo());
        }
    }

    private void displayProducts() throws RemoteException {
        List<IProduct> products = productService.getAllAvailable();
        System.out.println("\n===List of products===");

        if (products.isEmpty()) {
            System.out.println("No product.");
        }

        for (IProduct product : products) {
            System.out.println(product.getInfo());
        }
    }

    private void displayMyProducts() throws RemoteException {
        List<IProduct> products = productService.getByOwnerId(employeeId);
        System.out.println("\n===My products===");

        if (products.isEmpty()) {
            System.out.println("No product.");
        }

        for (IProduct product : products) {
            System.out.println(product.getInfo());
        }
    }

    private void buyProduct() throws RemoteException {
        System.out.println("\n===Buy product===");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID: ");
        int idProduct = scanner.nextInt();

        try {
            String result = productService.buy(idProduct, employeeId);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void putProductAsAvailableWithPrice() throws RemoteException {
        System.out.println("\n===Put product as available with price===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID to put as available: ");
        int idProduct = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        try {
            String result = productService.putAsAvailable(employeeId, idProduct, price);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void reviewProduct() throws RemoteException {
        System.out.println("\n===Review product===");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Product ID to review (it has to be at least sold once and available) : ");
        int idProduct = scanner.nextInt();

        System.out.print("Enter rating between 1 and 5 (mandatory) : ");
        int rating = scanner.nextInt();

        System.out.print("Enter comment (optional) : ");
        scanner.nextLine();
        String comment = scanner.nextLine();

        try {
            String result = productService.review(idProduct, employeeId, rating, comment);
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayReviewsOfAProduct() throws RemoteException {
        System.out.println("\n===Display review of a product===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter product ID : ");
        int productId = scanner.nextInt();

        try {
            List<IReview> reviews = productService.getReviews(productId);
            if (reviews.isEmpty()) {
                System.out.println("No review for this product.");
                return;
            }

            for (IReview review : reviews) {
                System.out.println(review.getInfo());
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addNewProduct() throws RemoteException {
        System.out.println("\n===Add new product===");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name : ");
        String name = scanner.nextLine();

        System.out.print("Enter price : ");
        double price = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Enter type (clothes, tech or food): ");
        String type = scanner.nextLine();

        try {
            IProduct product = productService.create(name, price, employeeId, type);
            System.out.println("Product created: " + product.getInfo());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void subscribeToProductType() throws RemoteException {
        System.out.println("\n===Subscribe to product type===");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter type (clothes, tech or food): ");
        String type = scanner.nextLine();

        try {
            productService.subscribe(observator, type);
            System.out.println("You are subscribed to be notified for " + type + " products.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
