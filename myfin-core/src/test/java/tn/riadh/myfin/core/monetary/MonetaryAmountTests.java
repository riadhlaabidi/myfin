package tn.riadh.myfin.core.monetary;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

public class MonetaryAmountTests {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency TND = Currency.getInstance("TND");
    private static final MonetaryAmount HUNDRED_USD = MonetaryAmount.of(new BigDecimal("100.00"), USD);
    private static final MonetaryAmount THIRTEEN_USD = MonetaryAmount.of(new BigDecimal("13.00"), USD);
    private static final MonetaryAmount FIFTY_TND = MonetaryAmount.of(new BigDecimal("50.000"), TND);
    private static final MonetaryAmount EIGHTY_SIX_TND = MonetaryAmount.of(new BigDecimal("96.000"), TND);

    @Test
    public void creatingWithNullAmountShouldThrowAnException() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> MonetaryAmount.of(null, USD));
    }

    @Test
    public void creatingWithNullCurrencyShouldThrowAnException() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> MonetaryAmount.of(new BigDecimal("12.4"), null));
    }

    @Test
    public void addingWithDifferentCurrenciesShouldThrowAnException() {
        assertThrowsExactly(CurrencyMismatchException.class, () -> HUNDRED_USD.add(FIFTY_TND));
    }

    @Test
    public void addingShouldReturnValidResult() {
        MonetaryAmount result = HUNDRED_USD.add(THIRTEEN_USD);
        assertTrue(new BigDecimal("113.00").equals(result.amount()));
        assertTrue(USD.equals(result.currency()));
    }

    @Test
    public void subtractingWithDifferentCurrenciesShouldThrowAnException() {
        assertThrowsExactly(CurrencyMismatchException.class, () -> FIFTY_TND.subtract(HUNDRED_USD));
    }

    @Test
    public void subtractingShouldReturnValidResult() {
        MonetaryAmount result = HUNDRED_USD.subtract(THIRTEEN_USD);
        assertTrue(new BigDecimal("87.00").equals(result.amount()));
        assertTrue(USD.equals(result.currency()));
    }

    @Test
    public void multiplyingShouldReturnValidResult() {
        MonetaryAmount result = EIGHTY_SIX_TND.multiply(5);
        assertTrue(new BigDecimal("480.000").equals(result.amount()));
        assertTrue(TND.equals(result.currency()));
    }
}
