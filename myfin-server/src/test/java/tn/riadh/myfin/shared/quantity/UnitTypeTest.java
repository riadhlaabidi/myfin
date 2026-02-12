package tn.riadh.myfin.shared.quantity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UnitTypeTest {

    @Test
    public void shouldDefineCorrectSemanticsForPiece() {
        UnitType piece = UnitType.PIECE;
        assertThat(piece.measurementType()).isEqualTo(MeasurementType.COUNT);
        assertThat(piece.isDivisible()).isFalse();
        assertThat(piece.scale()).isEqualTo(0);
        assertThat(piece.baseUnitRatio()).isEqualTo(1);
    }

    @Test
    public void shouldDefineCorrectSemanticsForKilogram() {
        UnitType piece = UnitType.KILOGRAM;
        assertThat(piece.measurementType()).isEqualTo(MeasurementType.WEIGHT);
        assertThat(piece.isDivisible()).isTrue();
        assertThat(piece.scale()).isEqualTo(3);
        assertThat(piece.baseUnitRatio()).isEqualTo(1000);
    }

    @Test
    public void shouldDefineCorrectSemanticsForLiter() {
        UnitType piece = UnitType.LITER;
        assertThat(piece.measurementType()).isEqualTo(MeasurementType.VOLUME);
        assertThat(piece.isDivisible()).isTrue();
        assertThat(piece.scale()).isEqualTo(3);
        assertThat(piece.baseUnitRatio()).isEqualTo(1000);
    }
}
