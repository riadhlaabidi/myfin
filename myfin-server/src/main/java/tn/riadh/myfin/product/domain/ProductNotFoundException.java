package tn.riadh.myfin.product.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException byId(ProductId productId) {
        return new ProductNotFoundException("Product with id " + productId.value() + " was not found");
    }

    public static ProductNotFoundException byBarcode(Barcode barcode) {
        return new ProductNotFoundException("Product with barcode " + barcode.value() + " was not found");
    }
}
