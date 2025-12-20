package tn.riadh.myfin.domain.supply;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class SupplyTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Supply.class);
        Supply supply1 = new Supply();
        Supply supply2 = new Supply();
        assertThat(supply1.getId()).isNull();
        assertThat(supply2.getId()).isNull();
        supply1.setId(1L);
        assertThat(supply1).isNotEqualTo(supply2);
        supply2.setId(supply1.getId());
        assertThat(supply1).isEqualTo(supply2);
        supply2.setId(2L);
        assertThat(supply1).isNotEqualTo(supply2);
    }
}
