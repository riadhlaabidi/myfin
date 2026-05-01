package tn.riadh.myfin.product.domain;

import java.util.Objects;

import org.jmolecules.ddd.types.ValueObject;

public class PluCode implements ValueObject {

    private static final Integer MAX_PLU = 99999;
    private final Integer value;

    private PluCode(Integer value) {
        Objects.requireNonNull(value, "PluCode cannot be null");

        if (value < 0 || value > MAX_PLU) {
            throw new IllegalArgumentException("PluCode outside range 0.." + MAX_PLU);
        }

        this.value = value;
    }

    static PluCode of(Integer value) {
        return new PluCode(value);
    }

    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof PluCode other &&
                value.intValue() == other.value.intValue();
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
