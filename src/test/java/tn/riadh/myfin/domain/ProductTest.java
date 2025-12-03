package tn.riadh.myfin.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class ProductTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Product.class);
        Product product1 = new Product();
        Product product2 = new Product();
        assertNull(product1.getId());
        assertNull(product2.getId());
        product1.setId(1L);
        product2.setId(product1.getId());
        assertEquals(product1, product2);
        product2.setId(2L);
        assertNotEquals(product1, product2);
        product1.setId(null);
        assertNotEquals(product1, product2);
    }
}
