package tn.riadh.myfin.shared.quantity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class QuantityTest {

    @Test
    public void shouldSetValidAmountAndScaleCorrectly() {
        Quantity kilo = assertDoesNotThrow(() -> Quantity.of(new BigDecimal("3"), UnitType.KILOGRAM));
        assertThat(kilo.amount().scale()).isEqualTo(UnitType.KILOGRAM.scale());
        assertThat(kilo.amount()).isEqualByComparingTo("3.000");

        kilo = assertDoesNotThrow(() -> Quantity.of(new BigDecimal("1.9"), UnitType.KILOGRAM));
        assertThat(kilo.amount().scale()).isEqualTo(UnitType.KILOGRAM.scale());
        assertThat(kilo.amount()).isEqualByComparingTo("1.900");
    }

    @Test
    public void shouldRejectAmountWithExcessPrecision() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Quantity.of(new BigDecimal("1.5"), UnitType.PIECE));
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Quantity.of(new BigDecimal("1.0001"), UnitType.KILOGRAM));
    }

    @Test
    public void shouldNormalizeAmountWithTrailingZeros() {
        Quantity piece = assertDoesNotThrow(() -> Quantity.of(new BigDecimal("1.000"), UnitType.PIECE));
        assertThat(piece.amount().scale()).isEqualTo(UnitType.PIECE.scale());
        assertThat(piece.amount()).isEqualByComparingTo("1");

        Quantity kilo = assertDoesNotThrow(() -> Quantity.of(new BigDecimal("1.17500"), UnitType.KILOGRAM));
        assertThat(kilo.amount().scale()).isEqualTo(UnitType.KILOGRAM.scale());
        assertThat(kilo.amount()).isEqualByComparingTo("1.175");
    }

    @Test
    public void shouldMaintainEquality() {
        Quantity kilo1 = Quantity.of(new BigDecimal("1.5"), UnitType.KILOGRAM);
        Quantity kilo2 = Quantity.of(new BigDecimal("1.500"), UnitType.KILOGRAM);

        assertThat(kilo1.equals(kilo1)).isTrue();

        assertThat(kilo1.equals(kilo2)).isTrue();
        assertThat(kilo2.equals(kilo1)).isTrue();

        assertThat(kilo2.equals(null)).isFalse();
        assertThat(kilo2.equals(new Object())).isFalse();
    }
}
