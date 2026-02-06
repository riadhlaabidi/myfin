package tn.riadh.myfin.product.domain;

import java.util.Objects;

import org.jmolecules.ddd.types.ValueObject;

public final class Barcode implements ValueObject {

    private final String value;

    public Barcode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Barcode cannot be null or empty");
        }
        this.value = value;
    }

    public static Barcode from(String value) {
        return new Barcode(value);
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Barcode)) {
            return false;
        }
        Barcode other = (Barcode) obj;
        return Objects.equals(value, other.value);
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
