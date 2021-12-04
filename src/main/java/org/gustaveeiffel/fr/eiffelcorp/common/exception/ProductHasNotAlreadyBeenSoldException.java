package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class ProductHasNotAlreadyBeenSoldException extends RuntimeException {

    public ProductHasNotAlreadyBeenSoldException() {
        super("The product has not already been sold once.");
    }
}
