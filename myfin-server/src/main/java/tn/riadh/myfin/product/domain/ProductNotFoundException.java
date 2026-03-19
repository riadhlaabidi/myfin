package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class ProductNotFoundException extends DomainException {

    private ProductNotFoundException(String message) {
        super("PRODUCT_NOT_FOUND", message);
    }

    public static ProductNotFoundException byId(ProductId productId) {
        return new ProductNotFoundException("Product with id " + productId.value() + " was not found");
    }

    public static ProductNotFoundException byBarcode(Barcode barcode) {
        return new ProductNotFoundException("Product with barcode " + barcode.value() + " was not found");
    }
}
