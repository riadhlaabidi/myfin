package tn.riadh.myfin.sale.infrastructure.web.rest;

import java.io.Serializable;

final class AddSaleLineCommand implements Serializable {
    private String sellableFormId;
    private String quantity;
    private String unit;

    public String getSellableFormId() {
        return sellableFormId;
    }

    public void setSellableFormId(String sellableFormId) {
        this.sellableFormId = sellableFormId;
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
