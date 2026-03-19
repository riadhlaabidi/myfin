package tn.riadh.myfin.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ProductStatusTest {

    @Test
    public void shouldParseLegalStatusCorrectly() {
        assertThat(ProductStatus.valueOf("ACTIVE")).isEqualTo(ProductStatus.ACTIVE);
        assertThat(ProductStatus.valueOf("DISCONTINUED")).isEqualTo(ProductStatus.DISCONTINUED);
    }

    @Test
    public void shouldFailToParseAnIllegalStatus() {
        assertThatThrownBy(() -> ProductStatus.valueOf("NOT_A_STATUS"))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
