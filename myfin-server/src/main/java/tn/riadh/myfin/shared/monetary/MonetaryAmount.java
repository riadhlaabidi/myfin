package tn.riadh.myfin.shared.monetary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

import org.jmolecules.ddd.types.ValueObject;

public final class MonetaryAmount implements ValueObject {

    private final BigDecimal amount;
    private final Currency currency;

    private MonetaryAmount(final BigDecimal amount, final Currency currency) {
        if (amount == null) {
            throw new IllegalArgumentException("amount cannot be null");
        }
        if (currency == null) {
            throw new IllegalArgumentException("currency cannot be null");
        }
        int scale = currency.getDefaultFractionDigits();
        this.amount = amount.setScale(scale, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public BigDecimal amount() {
        return this.amount;
    }

    public Currency currency() {
        return this.currency;
    }

    public static MonetaryAmount of(BigDecimal amount, Currency currency) {
        return new MonetaryAmount(amount, currency);
    }

    public MonetaryAmount add(MonetaryAmount augend) {
        ensureSameCurrency(augend);
        return new MonetaryAmount(this.amount.add(augend.amount), this.currency);
    }

    public MonetaryAmount subtract(MonetaryAmount subtrahend) {
        ensureSameCurrency(subtrahend);
        return new MonetaryAmount(this.amount.subtract(subtrahend.amount), currency);
    }

    public MonetaryAmount multiply(BigDecimal factor) {
        return new MonetaryAmount(this.amount.multiply(factor), currency);
    }

    public MonetaryAmount multiply(int factor) {
        return this.multiply(new BigDecimal(factor));
    }

    private void ensureSameCurrency(MonetaryAmount other) {
        if (!currency.equals(other.currency)) {
            throw CurrencyMismatchException.of(currency, other.currency);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MonetaryAmount)) {
            return false;
        }
        MonetaryAmount other = (MonetaryAmount) obj;
        return amount.equals(other.amount) && currency.equals(other.currency);
    }

    @Override
    public String toString() {
        return currency.getDisplayName() + " " + amount().toPlainString();
    }
}
