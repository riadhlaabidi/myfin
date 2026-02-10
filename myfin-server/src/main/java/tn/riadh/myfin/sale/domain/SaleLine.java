package tn.riadh.myfin.sale.domain;

import org.jmolecules.ddd.types.Entity;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.shared.quantity.Quantity;

public final class SaleLine implements Entity<Sale, SaleLineId> {
    private final SaleLineId id;
    private final SaleId saleId;
    private final ProductId productId;
    // WARN: would need to prevent invalid quantities for products
    private final Quantity quantity;

    private SaleLine(SaleLineId id, SaleId saleId, ProductId productId, Quantity quantity) {
        if (id == null) {
            throw new IllegalArgumentException("SaleLineId cannot be null");
        }
        if (saleId == null) {
            throw new IllegalArgumentException("SaleId cannot be null");
        }
        if (productId == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (quantity.isZeroAmount()) {
            throw new IllegalArgumentException("Quantity should be greater than zero");
        }
        this.id = id;
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static SaleLine create(SaleId saleId, ProductId productId, Quantity quantity) {
        return new SaleLine(SaleLineId.generate(), saleId, productId, quantity);
    }

    public static SaleLine reconstitute(SaleLineId id, SaleId saleId, ProductId productId, Quantity quantity) {
        return new SaleLine(id, saleId, productId, quantity);
    }

    @Override
    public SaleLineId getId() {
        return id;
    }

    public SaleId saleId() {
        return saleId;
    }

    public ProductId productId() {
        return productId;
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
        return "SaleLine{id=" + id
                + ", saleId" + saleId
                + ", productId=" + productId
                + ", quantity=" + quantity
                + "}";
    }
}
