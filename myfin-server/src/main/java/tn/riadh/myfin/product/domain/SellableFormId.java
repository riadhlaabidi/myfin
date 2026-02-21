package tn.riadh.myfin.product.domain;

import java.util.Objects;
import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

public class SellableFormId implements Identifier {

    private final UUID value;

    private SellableFormId(UUID value) {
        Objects.requireNonNull(value, "SellableFormId cannot be null");
        this.value = value;
    }

    public static SellableFormId generate() {
        return new SellableFormId(UUID.randomUUID());
    }

    public static SellableFormId from(String uuid) {
        return new SellableFormId(UUID.fromString(uuid));
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SellableFormId)) {
            return false;
        }
        SellableFormId other = (SellableFormId) obj;
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
