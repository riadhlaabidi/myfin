package tn.riadh.myfin.product.domain;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }

    public static ProductNotFoundException byId(ProductId productId) {
        return new ProductNotFoundException("Product with id " + productId.value() + " was not found");
    }

}
