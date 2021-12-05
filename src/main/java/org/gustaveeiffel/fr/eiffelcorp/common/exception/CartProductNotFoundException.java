package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class CartProductNotFoundException extends RuntimeException {

    public CartProductNotFoundException(int productId) {
        super("Cart product with product ID [" + productId + "] not found.");
    }
}
