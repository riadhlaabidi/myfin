package tn.riadh.myfin.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public final class EqualsHashCodeTestUtil {

    private EqualsHashCodeTestUtil() {
    }

    public static <T> void equalsAndHashCodeVerifier(Class<T> clazz) throws Exception {
        T domainObject1 = clazz.getConstructor().newInstance();
        // Same reference
        assertEquals(domainObject1, domainObject1);
        assertEquals(domainObject1.hashCode(), domainObject1.hashCode());
        // Test with an instance of another class
        Object testObject = new Object();
        assertNotEquals(domainObject1, testObject);
        // Test with an instance of the same domain class
        T domainObject2 = clazz.getConstructor().newInstance();
        assertNotEquals(domainObject1, domainObject2);
        assertEquals(domainObject1.hashCode(), domainObject2.hashCode());
    }

}
