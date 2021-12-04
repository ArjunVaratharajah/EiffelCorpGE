package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class EmployeeBudgetIsNotEnoughException extends RuntimeException {

    public EmployeeBudgetIsNotEnoughException(double employeeBudget, double productPrice) {
        super("Employee budget: " + employeeBudget + ", product price: " + productPrice + ", employee budget is not enough.");
    }

}
