package tn.riadh.myfin.sale.domain;

import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

/**
 * Identifier type for {@link SaleLine}.
 *
 * Represents the unique identity of a SaleLine instance. This type exists to
 * provide explicit typing and avoid misuse of raw identifier values.
 */
public final class SaleLineId implements Identifier {
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
