package tn.riadh.myfin.domain.common;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

public class MonetaryAmountTest {

    @Test
    public void equalsTest() {
        // same amount, same currency
        Currency c1 = Currency.getInstance("TND");
        MonetaryAmount ma1 = new MonetaryAmount(new BigDecimal("2.5"), c1);
        MonetaryAmount ma2 = new MonetaryAmount(new BigDecimal("2.5"), c1);
        assertThat(ma1).isEqualTo(ma2);

        // same currency, different amounts
        MonetaryAmount ma3 = new MonetaryAmount(new BigDecimal("2.55"), c1);
        assertThat(ma3).isNotEqualTo(ma2);

        // same amount, different currencies
        Currency c2 = Currency.getInstance("EUR");
        MonetaryAmount ma4 = new MonetaryAmount(new BigDecimal("2.55"), c2);
        assertThat(ma4).isNotEqualTo(ma3);

        // different amounts, different currencies
        MonetaryAmount ma5 = new MonetaryAmount(new BigDecimal("3.9"), c2);
        assertThat(ma5).isNotEqualTo(ma3);
    }

    @Test
    public void shouldScaleMonetaryAmountAccordingToCurrency() {
        Currency c1 = Currency.getInstance("TND"); // scale = 3
        MonetaryAmount ma1 = new MonetaryAmount(new BigDecimal("1.2345"), c1);
        assertThat(ma1.amount()).isEqualByComparingTo("1.235");

        Currency c2 = Currency.getInstance("USD"); // scale = 2
        MonetaryAmount ma2 = new MonetaryAmount(new BigDecimal("1.2345"), c2);
        assertThat(ma2.amount()).isEqualByComparingTo("1.23");

        Currency c3 = Currency.getInstance("JPY"); // scale = 0
        MonetaryAmount ma3 = new MonetaryAmount(new BigDecimal("1.2345"), c3);
        assertThat(ma3.amount()).isEqualByComparingTo("1.0");
    }

    @Test
    public void shouldThrowExceptionWhenAddingMonetaryAmountWithCurrencyMismatch() {
        Currency c1 = Currency.getInstance("TND");
        Currency c2 = Currency.getInstance("EUR");

        MonetaryAmount ma1 = new MonetaryAmount(new BigDecimal("1.5"), c1);
        MonetaryAmount ma2 = new MonetaryAmount(new BigDecimal("1.77"), c2);

        assertThatThrownBy(() -> ma1.add(ma2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldThrowExceptionWhenSubtractingMonetaryAmountWithCurrencyMismatch() {
        Currency c1 = Currency.getInstance("USD");
        Currency c2 = Currency.getInstance("EUR");

        MonetaryAmount ma1 = new MonetaryAmount(new BigDecimal("3.89"), c1);
        MonetaryAmount ma2 = new MonetaryAmount(new BigDecimal("1.32"), c2);

        assertThatThrownBy(() -> ma1.subtract(ma2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldRoundUsingCurrencyRulesWhenMultiplying() {
        Currency currency = Currency.getInstance("TND");
        MonetaryAmount ma1 = new MonetaryAmount(new BigDecimal("4.76211"), currency);
        MonetaryAmount ma2 = ma1.multiply(new BigDecimal("2.5"));
        assertThat(ma2.amount()).isEqualByComparingTo("11.905");
    }
}
