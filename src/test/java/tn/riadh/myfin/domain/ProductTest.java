package tn.riadh.myfin.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class ProductTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Product.class);
        Product product1 = new Product();
        Product product2 = new Product();
        assertThat(product1.getId()).isNull();
        assertThat(product2.getId()).isNull();
        product1.setId(1L);
        product2.setId(product1.getId());
        assertThat(product1).isEqualTo(product2);
        product2.setId(2L);
        assertThat(product1).isNotEqualTo(product2);
        product1.setId(null);
        assertThat(product1).isNotEqualTo(product2);
    }
}
