package tn.riadh.myfin.sale.domain;

import tn.riadh.myfin.product.domain.ProductId;

public final class SaleLine {
    private final SaleLineId id;
    private final SaleId saleId;
    private final ProductId productId;
    private final long quantity;

    private SaleLine(SaleId saleId, ProductId productId, long quantity) {
        if (saleId == null) {
            throw new IllegalArgumentException("SaleId cannot be null");
        }
        if (productId == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity should be greater than zero");
        }
        this.id = SaleLineId.generate();
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static SaleLine create(SaleId saleId, ProductId productId, long quantity) {
        return new SaleLine(saleId, productId, quantity);
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
