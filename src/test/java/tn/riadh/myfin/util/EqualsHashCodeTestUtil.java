package tn.riadh.myfin.util;

import static org.assertj.core.api.Assertions.assertThat;

public final class EqualsHashCodeTestUtil {

    public static <T> void equalsAndHashCodeVerifier(Class<T> clazz) throws Exception {
        T domainObject1 = clazz.getConstructor().newInstance();
        // Same reference
        assertThat(domainObject1).isEqualTo(domainObject1);
        assertThat(domainObject1.hashCode()).isEqualTo(domainObject1.hashCode());
        // Test with an instance of another class
        Object testObject = new Object();
        assertThat(domainObject1).isNotEqualTo(testObject);
        // Test with an instance of the same domain class
        T domainObject2 = clazz.getConstructor().newInstance();
        assertThat(domainObject1).isNotEqualTo(domainObject2);
        assertThat(domainObject1.hashCode()).isEqualTo(domainObject2.hashCode());
    }

    private EqualsHashCodeTestUtil() {
    }
}
