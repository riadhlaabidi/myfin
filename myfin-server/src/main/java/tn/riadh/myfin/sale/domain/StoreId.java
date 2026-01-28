package tn.riadh.myfin.sale.domain;

import java.util.UUID;

public final class StoreId {

    private final UUID value;

    private StoreId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("StoreId cannot be null");
        }
        this.value = value;
    }

    public UUID value() {
        return value;
    }

    public static StoreId generate() {
        return new StoreId(UUID.randomUUID());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StoreId)) {
            return false;
        }
        StoreId other = (StoreId) obj;
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
