package tn.riadh.myfin.application.monetary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.Currency;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.domain.common.MonetaryAmount;
import tn.riadh.myfin.infrastructure.context.MonetaryContext;

public class MonetaryFactoryTest {

    @Test
    public void shouldCreateMonetaryAmountsUsingCurrencyFromMonetaryContext() {
        Currency currency = Currency.getInstance("EUR");
        MonetaryContext.setCurrency(currency);

        MonetaryFactory monetaryFactory = new MonetaryFactory();
        MonetaryAmount ma = monetaryFactory.amount(new BigDecimal("123.333"));
        assertThat(ma.currency()).isEqualTo(currency);
    }

    @Test
    public void shouldFailWhenMonetaryContextIsNotInitialized() {
        MonetaryFactory monetaryFactory = new MonetaryFactory();
        assertThatThrownBy(() -> monetaryFactory.amount(new BigDecimal("10")))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Monetary context is not initialized");
    }
}
