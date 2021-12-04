package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductIsNotAvailableException extends RuntimeException {

    public ProductIsNotAvailableException() {
        super("The product is not available.");
    }

}
