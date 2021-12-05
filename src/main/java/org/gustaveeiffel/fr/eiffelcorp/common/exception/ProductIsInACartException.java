package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductIsInACartException extends RuntimeException {

    public ProductIsInACartException() {
        super("The product is already in a cart.");
    }

}
