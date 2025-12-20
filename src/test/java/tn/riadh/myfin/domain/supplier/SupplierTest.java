package tn.riadh.myfin.domain.supplier;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class SupplierTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Supplier.class);
        Supplier supplier1 = new Supplier();
        Supplier supplier2 = new Supplier();
        assertThat(supplier1.getId()).isNull();
        assertThat(supplier2.getId()).isNull();
        supplier1.setId(1L);
        assertThat(supplier1).isNotEqualTo(supplier2);
        supplier2.setId(supplier1.getId());
        assertThat(supplier1).isEqualTo(supplier2);
        supplier2.setId(2L);
        assertThat(supplier1).isNotEqualTo(supplier2);
    }
}
