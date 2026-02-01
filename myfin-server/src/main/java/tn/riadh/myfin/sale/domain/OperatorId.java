package tn.riadh.myfin.sale.domain;

import java.util.UUID;

import org.jmolecules.ddd.types.Identifier;

public final class OperatorId implements Identifier {

    private final UUID value;

    private OperatorId(UUID value) {
        if (value == null) {
            throw new IllegalArgumentException("OperatorId cannot be null");
        }
        this.value = value;
    }

    public UUID value() {
        return value;
    }

    public static OperatorId generate() {
        return new OperatorId(UUID.randomUUID());
    }

    public static OperatorId from(String id) {
        return new OperatorId(UUID.fromString(id));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OperatorId)) {
            return false;
        }
        OperatorId other = (OperatorId) obj;
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
