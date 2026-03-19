package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

class ProductWithNoSellableFormsException extends DomainException {

    public ProductWithNoSellableFormsException() {
        super("PRODUCT_WITH_NO_SELLABLE_FORMS", "Product should have at least 1 sellable form");
    }
}
