package tn.riadh.myfin.domain.product;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class ProductCategoryTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Product.class);
        ProductCategory category1 = new ProductCategory();
        ProductCategory category2 = new ProductCategory();
        assertThat(category1.getId()).isNull();
        assertThat(category2.getId()).isNull();
        category1.setId(1L);
        category2.setId(category1.getId());
        assertThat(category1).isEqualTo(category2);
        category2.setId(2L);
        assertThat(category1).isNotEqualTo(category2);
        category1.setId(null);
        assertThat(category1).isNotEqualTo(category2);
    }
}
