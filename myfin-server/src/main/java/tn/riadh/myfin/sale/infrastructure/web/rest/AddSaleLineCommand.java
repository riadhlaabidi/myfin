package tn.riadh.myfin.sale.infrastructure.web.rest;

import java.io.Serializable;

final class AddSaleLineCommand implements Serializable {
    private String productId;
    private String quantity;
    private String unit;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
