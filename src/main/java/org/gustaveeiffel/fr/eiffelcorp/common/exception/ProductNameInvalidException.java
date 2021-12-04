package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductNameInvalidException extends RuntimeException {

    public ProductNameInvalidException(String name) {
        super("The name [" + name + "] is invalid.");
    }
}
