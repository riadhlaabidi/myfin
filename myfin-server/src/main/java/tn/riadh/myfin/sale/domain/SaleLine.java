package tn.riadh.myfin.sale.domain;

import java.util.Objects;

import org.jmolecules.ddd.types.Entity;

import tn.riadh.myfin.product.domain.SellableFormId;
import tn.riadh.myfin.shared.quantity.Quantity;

public final class SaleLine implements Entity<Sale, SaleLineId> {
    private final SaleLineId id;
    private final SaleId saleId;
    private final SellableFormId sellableFormId;
    private final Quantity quantity;

    private SaleLine(SaleLineId id, SaleId saleId, SellableFormId sellableFormId, Quantity quantity) {
        Objects.requireNonNull(id, "SaleLineId cannot be null");
        Objects.requireNonNull(saleId, "SaleId cannot be null");
        Objects.requireNonNull(sellableFormId, "SellableFormId cannot be null");
        Objects.requireNonNull(quantity, "quantity cannot be null");

        this.id = id;
        this.saleId = saleId;
        this.sellableFormId = sellableFormId;
        this.quantity = quantity;
    }

    public static SaleLine create(SaleId saleId, SellableFormId sellableFormId, Quantity quantity) {
        return new SaleLine(SaleLineId.generate(), saleId, sellableFormId, quantity);
    }

    public static SaleLine reconstitute(SaleLineId id, SaleId saleId, SellableFormId sellableFormId,
            Quantity quantity) {
        return new SaleLine(id, saleId, sellableFormId, quantity);
    }

    @Override
    public SaleLineId getId() {
        return id;
    }

    public SaleId saleId() {
        return saleId;
    }

    public SellableFormId sellableFormId() {
        return sellableFormId;
    }

    public Quantity quantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SaleLine)) {
            return false;
        }
        SaleLine other = (SaleLine) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
