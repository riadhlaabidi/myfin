package tn.riadh.myfin.core.sale.domain;

import tn.riadh.myfin.core.product.domain.ProductId;

public final class SaleLine {
    private final SaleId saleId;
    private final ProductId productId;
    private final long quantity;

    private SaleLine(SaleId saleId, ProductId productId, long quantity) {
        this.saleId = saleId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static SaleLine create(SaleId saleId, ProductId productId, long quantity) {
        return new SaleLine(saleId, productId, quantity);
    }

}
