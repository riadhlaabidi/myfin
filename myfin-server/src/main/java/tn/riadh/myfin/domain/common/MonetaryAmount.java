package tn.riadh.myfin.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

public class MonetaryAmount {

    private BigDecimal amount;
    private Currency currency;

    public MonetaryAmount(BigDecimal amount, Currency currency) {
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

    public MonetaryAmount add(MonetaryAmount augend) {
        requireSameCurrency(augend);
        return new MonetaryAmount(this.amount.add(augend.amount), this.currency);
    }

    public MonetaryAmount subtract(MonetaryAmount subtrahend) {
        requireSameCurrency(subtrahend);
        return new MonetaryAmount(this.amount.subtract(subtrahend.amount), currency);
    }

    public MonetaryAmount multiply(BigDecimal factor) {
        return new MonetaryAmount(this.amount.multiply(factor), currency);
    }

    public MonetaryAmount multiply(int factor) {
        return this.multiply(new BigDecimal(factor));
    }

    private void requireSameCurrency(MonetaryAmount other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MonetaryAmount other = (MonetaryAmount) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return currency.getDisplayName() + " " + amount().toPlainString();
    }
}
