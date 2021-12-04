package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class NotProductOwnerException extends RuntimeException {

    public NotProductOwnerException(String productName, String employeeFullname) {
        super("You can't update product availability because the product " + productName + " is owned by employee " + employeeFullname + ".");
    }

}
