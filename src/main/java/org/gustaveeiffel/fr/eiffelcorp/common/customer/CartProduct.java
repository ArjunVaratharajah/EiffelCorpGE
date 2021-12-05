package org.gustaveeiffel.fr.eiffelcorp.common.customer;

public class CartProduct {

    private int id;
    private int productId;
    private int customerId;

    public CartProduct(int id, int productId, int customerId) {
        this.id = id;
        this.productId = productId;
        this.customerId = customerId;
    }

    public int getProducId() {
        return productId;
    }
}
