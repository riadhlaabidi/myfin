package tn.riadh.myfin.domain;

import tn.riadh.myfin.domain.enumeration.OperationType;

import java.time.Instant;

public class Operation extends AbstractEntity {
    private Instant date;
    private Product product;
    private OperationType type;
    private int units;

    public Operation(Instant date, Product product, OperationType type, int units) {
        this.date = date;
        this.product = product;
        this.type = type;
        this.units = units;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Product{id=" + getId() +
                ", date=" + date +
                ", product=" + product.getId() +
                ", operationType" + type.name() +
                ", units" + units +
                "}";
    }
}
