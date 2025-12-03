package tn.riadh.myfin.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.util.EqualsHashCodeTestUtil;

public class IventoryTest {

    @Test
    public void equalsTest() throws Exception {
        EqualsHashCodeTestUtil.equalsAndHashCodeVerifier(Inventory.class);
        Inventory inventory1 = new Inventory();
        Inventory inventory2 = new Inventory();
        assertNull(inventory1.getId());
        assertNull(inventory2.getId());
        inventory1.setId(1L);
        inventory2.setId(inventory1.getId());
        assertEquals(inventory1, inventory2);
        inventory2.setId(2L);
        assertNotEquals(inventory1, inventory2);
        inventory1.setId(null);
        assertNotEquals(inventory1, inventory2);
    }
}
