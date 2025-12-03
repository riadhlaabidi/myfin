package tn.riadh.myfin.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class ProductCategoryTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Product.class);
        ProductCategory category1 = new ProductCategory();
        ProductCategory category2 = new ProductCategory();
        assertNull(category1.getId());
        assertNull(category2.getId());
        category1.setId(1L);
        category2.setId(category1.getId());
        assertEquals(category1, category2);
        category2.setId(2L);
        assertNotEquals(category1, category2);
        category1.setId(null);
        assertNotEquals(category1, category2);
    }
}
