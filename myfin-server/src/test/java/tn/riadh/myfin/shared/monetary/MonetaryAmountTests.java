package tn.riadh.myfin.shared.monetary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

public class MonetaryAmountTests {

    private static final Currency USD = Currency.getInstance("USD");
    private static final Currency EUR = Currency.getInstance("EUR");
    private static final Currency TND = Currency.getInstance("TND");
    private static final MonetaryAmount HUNDRED_USD = MonetaryAmount.of(new BigDecimal("100.00"), USD);
    private static final MonetaryAmount THIRTEEN_USD = MonetaryAmount.of(new BigDecimal("13.00"), USD);
    private static final MonetaryAmount FIFTY_TND = MonetaryAmount.of(new BigDecimal("50.000"), TND);
    private static final MonetaryAmount EIGHTY_SIX_TND = MonetaryAmount.of(new BigDecimal("96.000"), TND);

    @Test
    public void equalsTest() {
        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("2.5"), TND);
        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("2.5"), TND);
        assertThat(ma1).isEqualTo(ma2);

        MonetaryAmount ma3 = MonetaryAmount.of(new BigDecimal("2.55"), TND);
        assertThat(ma3).isNotEqualTo(ma2);

        MonetaryAmount ma4 = MonetaryAmount.of(new BigDecimal("2.55"), EUR);
        assertThat(ma4).isNotEqualTo(ma3);
    }

    @Test
    public void shouldScaleMonetaryAmountAccordingToCurrency() {
        MonetaryAmount ma1 = MonetaryAmount.of(new BigDecimal("1.2345"), TND);
        assertThat(ma1.amount()).isEqualByComparingTo("1.235");

        MonetaryAmount ma2 = MonetaryAmount.of(new BigDecimal("1.2345"), USD);
        assertThat(ma2.amount()).isEqualByComparingTo("1.23");
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
        assertThatThrownBy(() -> FIFTY_TND.subtract(HUNDRED_USD))
                .isExactlyInstanceOf(CurrencyMismatchException.class)
                .hasMessageContaining("Currency mismatch");
    }

    @Test
    public void shouldReturnValidResultWhenSubtracting() {
        MonetaryAmount result = HUNDRED_USD.subtract(THIRTEEN_USD);
        assertThat(result.amount()).isEqualTo(new BigDecimal("87.00"));
        assertThat(result.currency()).isEqualTo(USD);
    }

    @Test
    public void shouldReturnValidResultWhenMultiplying() {
        MonetaryAmount result = EIGHTY_SIX_TND.multiply(5);
        assertThat(result.amount()).isEqualTo(new BigDecimal("480.000"));
        assertThat(result.currency()).isEqualTo(TND);
    }

    @Test
    public void shouldRoundUsingCurrencyRulesWhenMultiplying() {
        MonetaryAmount ma = MonetaryAmount.of(new BigDecimal("4.76211"), TND);
        MonetaryAmount result = ma.multiply(new BigDecimal("2.5"));
        assertThat(result.amount()).isEqualByComparingTo("11.905");
    }
}
