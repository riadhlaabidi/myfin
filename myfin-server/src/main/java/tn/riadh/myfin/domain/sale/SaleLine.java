package tn.riadh.myfin.domain.sale;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.common.MonetaryAmount;

public class SaleLine extends AbstractEntity {
    private Long productId;
    private int quantity;
    private MonetaryAmount unitPrice;

    public SaleLine() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MonetaryAmount getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(MonetaryAmount unitPrice) {
        this.unitPrice = unitPrice;
    }

    public MonetaryAmount lineTotal() {
        return unitPrice.multiply(quantity);
    }
}
