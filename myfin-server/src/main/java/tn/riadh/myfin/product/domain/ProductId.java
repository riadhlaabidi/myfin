package tn.riadh.myfin.product.domain;

import java.util.Objects;
import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

public final class ProductId implements Identifier {

    private final UUID value;

    private ProductId(final UUID value) {
        Objects.requireNonNull(value, "ProductId cannot be null");
        this.value = value;
    }

    public UUID value() {
        return value;
    }

    public static ProductId of(UUID uuid) {
        return new ProductId(uuid);
    }

    public static ProductId from(String uuid) {
        return new ProductId(UUID.fromString(uuid));
    }

    public static ProductId generate() {
        return new ProductId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductId)) {
            return false;
        }
        ProductId other = (ProductId) obj;
        return value.equals(other.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
