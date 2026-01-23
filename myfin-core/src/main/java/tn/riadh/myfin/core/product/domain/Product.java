package tn.riadh.myfin.core.product.domain;

public final class Product {
    private final ProductId id;
    private ProductStatus status;

    private Product() {
        this.id = ProductId.generate();
        this.status = ProductStatus.ACTIVE;
    }

    public ProductId id() {
        return id;
    }

    public ProductStatus status() {
        return status;
    }

    public static Product create() {
        return new Product();
    }
}
