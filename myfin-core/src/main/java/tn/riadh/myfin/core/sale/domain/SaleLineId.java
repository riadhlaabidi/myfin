package tn.riadh.myfin.core.sale.domain;

import java.util.UUID;

public final class SaleLineId {
    private final UUID value;

    private SaleLineId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("SaleLineId cannot be null");
        }
        this.value = value;
    }

    public static SaleLineId generate() {
        return new SaleLineId(UUID.randomUUID());
    }

    public UUID value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SaleLineId)) {
            return false;
        }
        SaleLineId other = (SaleLineId) obj;
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
