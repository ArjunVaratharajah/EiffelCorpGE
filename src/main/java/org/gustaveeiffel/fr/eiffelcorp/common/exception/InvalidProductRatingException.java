package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class InvalidProductRatingException extends RuntimeException {

    public InvalidProductRatingException() {
        super("The product rating must be between 1 and 5.");
    }
}
