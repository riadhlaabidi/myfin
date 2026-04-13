package tn.riadh.myfin.shared.quantity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UnitOfMesureTest {
    @Test
    public void shouldDefineCorrectSemanticsForPiece() {
        UnitOfMesure piece = UnitOfMesure.PIECE;
        assertThat(piece.measurementType()).isEqualTo(MeasurementType.COUNT);
        assertThat(piece.scale()).isEqualTo(0);
    }

    @Test
    public void shouldDefineCorrectSemanticsForKilogram() {
        UnitOfMesure piece = UnitOfMesure.KILOGRAM;
        assertThat(piece.measurementType()).isEqualTo(MeasurementType.WEIGHT);
        assertThat(piece.scale()).isEqualTo(3);
    }
}
