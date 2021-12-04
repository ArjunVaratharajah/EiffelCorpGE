package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductPriceInvalidException extends RuntimeException {

    public ProductPriceInvalidException(double price) {
        super("The price " + price + " is invalid.");
    }
}
