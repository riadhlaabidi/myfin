package tn.riadh.myfin.infrastructure.context;

import java.util.Currency;

public final class MonetaryContext {

    private static final ThreadLocal<Currency> CURRENT = new ThreadLocal<>();

    private MonetaryContext() {
    }

    public static void setCurrency(Currency currency) {
        CURRENT.set(currency);
    }

    public static Currency getCurrency() {
        Currency currency = CURRENT.get();
        if (currency == null) {
            throw new IllegalStateException("Monetary context is not initialized");
        }
        return currency;
    }

    public static void clear() {
        CURRENT.remove();
    }
}
