package tn.riadh.myfin.shared.monetary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    public void equalsTest() {
        // same amount, same currency
        Currency c1 = Currency.getInstance("TND");
        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("2.5"), c1);
        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("2.5"), c1);
        assertThat(ma1).isEqualTo(ma2);

        // same currency, different amounts
        MonetaryAmount ma3 = MonetaryAmount.of(new BigDecimal("2.55"), c1);
        assertThat(ma3).isNotEqualTo(ma2);

        // same amount, different currencies
        Currency c2 = Currency.getInstance("EUR");
        MonetaryAmount ma4 = MonetaryAmount.of(new BigDecimal("2.55"), c2);
        assertThat(ma4).isNotEqualTo(ma3);

        // different amounts, different currencies
        MonetaryAmount ma5 = MonetaryAmount.of(new BigDecimal("3.9"), c2);
        assertThat(ma5).isNotEqualTo(ma3);
    }

    @Test
    public void shouldScaleMonetaryAmountAccordingToCurrency() {
        Currency c1 = Currency.getInstance("TND"); // scale = 3
        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("1.2345"), c1);
        assertThat(ma1.amount()).isEqualByComparingTo("1.235");

        Currency c2 = Currency.getInstance("USD"); // scale = 2
        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("1.2345"), c2);
        assertThat(ma2.amount()).isEqualByComparingTo("1.23");

        Currency c3 = Currency.getInstance("JPY"); // scale = 0
        MonetaryAmount ma3 = MonetaryAmount.of(new BigDecimal("1.2345"), c3);
        assertThat(ma3.amount()).isEqualByComparingTo("1.0");
    }

    @Test
    public void shouldThrowExceptionWhenAddingMonetaryAmountWithCurrencyMismatch() {
        Currency c1 = Currency.getInstance("TND");
        Currency c2 = Currency.getInstance("EUR");

        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("1.5"), c1);
        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("1.77"), c2);

        assertThatThrownBy(() -> ma1.add(ma2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldThrowExceptionWhenSubtractingMonetaryAmountWithCurrencyMismatch() {
        Currency c1 = Currency.getInstance("USD");
        Currency c2 = Currency.getInstance("EUR");

        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("3.89"), c1);
        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("1.32"), c2);

        assertThatThrownBy(() -> ma1.subtract(ma2))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldRoundUsingCurrencyRulesWhenMultiplying() {
        Currency currency = Currency.getInstance("TND");
        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("4.76211"), currency);
        MonetaryAmount ma2 = ma1.multiply(new BigDecimal("2.5"));
        assertThat(ma2.amount()).isEqualByComparingTo("11.905");
    }

    @Test
    public void shouldThrowAnExceptionWhenCreatingWithNullAmount() {
        assertThatThrownBy(() -> MonetaryAmount.of(null, USD))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("amount");
    }

    @Test
    public void shouldThrowAnExceptionWhenCreatingWithNullCurrency() {
        assertThatThrownBy(() -> MonetaryAmount.of(new BigDecimal("12.4"), null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("currency");
    }

    @Test
    public void shouldThrowAnExceptionWhenAddingWithDifferentCurrencies() {
        assertThatThrownBy(() -> HUNDRED_USD.add(FIFTY_TND))
                .isExactlyInstanceOf(CurrencyMismatchException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldReturnValidResultWhenAdding() {
        MonetaryAmount result = HUNDRED_USD.add(THIRTEEN_USD);
        assertThat(result.amount()).isEqualTo(new BigDecimal("113.00"));
        assertThat(result.currency()).isEqualTo(USD);
    }

    @Test
    public void shouldThrowAnExceptionWhenSubtractingWithDifferentCurrencies() {
        assertThatThrownBy(() -> FIFTY_TND.subtract(HUNDRED_USD)).isExactlyInstanceOf(type);
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
