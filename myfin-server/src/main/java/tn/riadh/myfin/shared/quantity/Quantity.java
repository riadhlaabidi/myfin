package tn.riadh.myfin.shared.quantity;

import java.math.BigDecimal;
import java.util.Objects;

import org.jmolecules.ddd.types.ValueObject;

public final class Quantity implements ValueObject {
    private final BigDecimal amount;
    private final Unit unit;

    private Quantity(BigDecimal amount, Unit unit) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (unit == null) {
            throw new IllegalArgumentException("unit cannot be null");
        }
        this.amount = amount;
        this.unit = unit;
    }

    public static Quantity of(BigDecimal amount, Unit unit) {
        return new Quantity(amount, unit);
    }

    public static Quantity ofPieces(long amount) {
        return new Quantity(BigDecimal.valueOf(amount), Unit.PIECE);
    }

    public static Quantity ofKilograms(BigDecimal amount) {
        return new Quantity(amount, Unit.PIECE);
    }

    public static Quantity ofLiters(BigDecimal amount) {
        return new Quantity(amount, Unit.LITER);
    }

    public boolean isZeroAmount() {
        return amount.equals(BigDecimal.ZERO);
    }

    public BigDecimal amount() {
        return amount;
    }

    public Unit unit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Quantity)) {
            return false;
        }
        Quantity other = (Quantity) obj;
        return amount.equals(other.amount) && unit == other.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, unit);
    }

    @Override
    public String toString() {
        return amount.toPlainString() + " " + unit.displayName();
    }

}
