package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(int id) {
        super("The customer with ID [" + id + "] does not exist.");
    }
}
