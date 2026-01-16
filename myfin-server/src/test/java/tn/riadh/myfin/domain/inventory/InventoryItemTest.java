package tn.riadh.myfin.domain.inventory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class InventoryItemTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(InventoryItem.class);
        InventoryItem inventory1 = new InventoryItem();
        InventoryItem inventory2 = new InventoryItem();
        assertThat(inventory1.getId()).isNull();
        assertThat(inventory2.getId()).isNull();
        inventory1.setId(1L);
        inventory2.setId(inventory1.getId());
        assertThat(inventory1).isEqualTo(inventory2);
        inventory2.setId(2L);
        assertThat(inventory1).isNotEqualTo(inventory2);
        inventory1.setId(null);
        assertThat(inventory1).isNotEqualTo(inventory2);
    }
}
