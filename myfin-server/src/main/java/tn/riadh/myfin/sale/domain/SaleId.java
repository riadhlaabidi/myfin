package tn.riadh.myfin.sale.domain;

import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

/**
 * Identifier type for Sale.
 *
 * Represents the unique identity of a Sale instance. This type exists to
 * provide explicit typing and avoid misuse of raw identifier values.
 */
public final class SaleId implements Identifier {

    private final UUID value;

    private SaleId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("SaleId cannot be null");
        }
        this.value = value;
    }

    public UUID value() {
        return value;
    }

    public static SaleId generate() {
        return new SaleId(UUID.randomUUID());
    }

    public static SaleId from(String id) {
        return new SaleId(UUID.fromString(id));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SaleId)) {
            return false;
        }
        SaleId other = (SaleId) obj;
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
