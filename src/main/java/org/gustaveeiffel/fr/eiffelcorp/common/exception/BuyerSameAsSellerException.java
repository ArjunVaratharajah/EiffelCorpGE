package org.gustaveeiffel.fr.eiffelcorp.common.exception;

public class BuyerSameAsSellerException extends RuntimeException {

	public BuyerSameAsSellerException() {
		super("The buyer can't buy his own product.");
	}

}
