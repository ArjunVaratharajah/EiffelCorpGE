package org.gustaveeiffel.fr.eiffelcorp.ifshare.server.product;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import org.gustaveeiffel.fr.eiffelcorp.common.product.IProductService;

public class ProductServerIfShare {

    public static void main(String args[]) throws Exception {
        LocateRegistry.createRegistry(1099);
        IProductService productService = new ProductService();
        Naming.rebind("rmi://localhost:1099/ProductService", productService);
        System.out.println("Product Server has started on port 1099.");
    }

}
