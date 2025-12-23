package tn.riadh.myfin.domain.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

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

    public MonetaryAmount divide(BigDecimal divisor) {
        return new MonetaryAmount(this.amount.divide(amount), currency);
    }

    private void requireSameCurrency(MonetaryAmount other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
    }
}
