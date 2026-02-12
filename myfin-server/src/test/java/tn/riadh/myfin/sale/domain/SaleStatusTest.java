package tn.riadh.myfin.sale.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class SaleStatusTest {

    @Test
    public void shouldParseLegalStatusCorrectly() {
        assertThat(SaleStatus.valueOf("OPEN")).isEqualTo(SaleStatus.OPEN);
        assertThat(SaleStatus.valueOf("VOIDED")).isEqualTo(SaleStatus.VOIDED);
        assertThat(SaleStatus.valueOf("COMPLETED")).isEqualTo(SaleStatus.COMPLETED);
    }

    @Test
    public void shouldFailToParseAnIllegalStatus() {
        assertThatThrownBy(() -> SaleStatus.valueOf("NOT_A_STATUS"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
