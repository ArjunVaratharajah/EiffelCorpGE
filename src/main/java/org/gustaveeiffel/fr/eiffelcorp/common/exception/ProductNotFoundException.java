package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(int id) {
        super("The product with ID [" + id + "] does not exist.");
    }
}
