package tn.riadh.myfin.core.monetary;

import java.util.Currency;

public class CurrencyMismatchException extends RuntimeException {

    public CurrencyMismatchException(String message) {
        super(message);
    }

    public static CurrencyMismatchException of(Currency c1, Currency c2) {
        return new CurrencyMismatchException(
                "Currency mismatch: " + c1.getCurrencyCode() + " and " + c2.getCurrencyCode());
    }
}
