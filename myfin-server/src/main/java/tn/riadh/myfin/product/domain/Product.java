package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.quantity.UnitType;

public final class Product {
    private final ProductId id;
    private final Barcode barcode;
    private ProductStatus status;
    private final UnitType unit;

    private Product(ProductId id, Barcode barcode, ProductStatus status, UnitType unit) {
        if (id == null) {
            throw new IllegalArgumentException("ProductId cannot be null");
        }
        if (barcode == null) {
            throw new IllegalArgumentException("Barcode cannot be null");
        }
        this.id = id;
        this.barcode = barcode;
        this.status = status;
        this.unit = unit;
    }

    public static Product create(String barcode, UnitType unit) {
        return new Product(ProductId.generate(), Barcode.from(barcode), ProductStatus.ACTIVE, unit);
    }

    public ProductId id() {
        return id;
    }

    public Barcode barcode() {
        return barcode;
    }

    public ProductStatus status() {
        return status;
    }

    public UnitType unit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
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
