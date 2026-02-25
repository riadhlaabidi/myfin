package tn.riadh.myfin.product.domain;

import java.util.Objects;

import org.jmolecules.ddd.types.ValueObject;

public final class ProductName implements ValueObject {

    private final String value;

    private static final int MAX_LENGTH = 255;

    private ProductName(String value) {
        Objects.requireNonNull(value, "ProductName cannot be null");
        if (value.isBlank()) {
            throw new IllegalArgumentException("ProductName cannot be empty");
        }
        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("ProductName should not exceed 255 characters");
        }
        this.value = value;
    }

    public static ProductName of(String value) {
        return new ProductName(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductName)) {
            return false;
        }
        ProductName other = (ProductName) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
