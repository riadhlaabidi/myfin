package tn.riadh.myfin.product.domain;

import java.util.Objects;
import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

final class CategoryId implements Identifier {

    private final UUID value;

    private CategoryId(UUID value) {
        Objects.requireNonNull(value, "CategoryId cannot be null");
        this.value = value;
    }

    public static CategoryId generate() {
        return new CategoryId(UUID.randomUUID());
    }

    public static CategoryId of(String uuid) {
        return new CategoryId(UUID.fromString(uuid));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CategoryId)) {
            return false;
        }
        CategoryId other = (CategoryId) obj;
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
