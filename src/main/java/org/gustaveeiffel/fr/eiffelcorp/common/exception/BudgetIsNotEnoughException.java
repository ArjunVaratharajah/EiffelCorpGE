package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class BudgetIsNotEnoughException extends RuntimeException {

    public BudgetIsNotEnoughException(double budget, double price) {
        super("Budget: " + budget + ", price: " + price + ", budget is not enough.");
    }

}
