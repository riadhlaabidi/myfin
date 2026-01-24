package tn.riadh.myfin.core.monetary;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

public class MonetaryAmountTests {

    @Test
    public void creatingMonetaryAmountWithNullAmountShouldThrowAnException() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> MonetaryAmount.of(null, Currency.getInstance("USD")));
    }

    @Test
    public void creatingMonetaryAmountWithNullCurrencyShouldThrowAnException() {
        assertThrowsExactly(IllegalArgumentException.class,
                () -> MonetaryAmount.of(new BigDecimal("12.4"), null));
    }

    @Test
    public void addingMonetaryAmountsWithDifferentCurrenciesShouldThrowAnException() {
        MonetaryAmount a = MonetaryAmount.of(new BigDecimal("100.14"), Currency.getInstance("USD"));
        MonetaryAmount b = MonetaryAmount.of(new BigDecimal("130.14"), Currency.getInstance("TND"));

        assertThrowsExactly(IllegalArgumentException.class, () -> a.add(b));
        assertTrue(new BigDecimal("100.14").equals(a.amount()));
        assertTrue("USD".equals(a.currency().getCurrencyCode()));
    }
}
