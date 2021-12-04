package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int id) {
        super("The employee with ID [" + id + "] does not exist.");
    }

}
