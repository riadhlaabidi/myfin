package tn.riadh.myfin.domain.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class AuthorityTest {

    @Test
    public void equalsTest() {
        Authority authority1 = new Authority("ROLE_ADMIN");
        Authority authority2 = new Authority("ROLE_ADMIN");
        Authority authority3 = new Authority("ROLE_USER");
        assertThat(authority1).isEqualTo(authority2);
        assertThat(authority1.hashCode()).isEqualTo(authority2.hashCode());
        assertThat(authority1).isNotEqualTo(authority3);
        assertThat(authority1.hashCode()).isNotEqualTo(authority3.hashCode());
    }

    @Test
    public void shouldNotAllowAuthorityNameToBeNull() {
        assertThatThrownBy(() -> new Authority(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("authority name");
    }

    @Test
    public void shouldNotBeEqualToOtherTypes() {
        Authority authority = new Authority("ROLE_ADMIN");
        assertThat(authority).isNotEqualTo(null);
        assertThat(authority).isNotEqualTo("ROLE_ADMIN");
    }

    @Test
    public void shouldBehaveCorrectlyInCollections() {
        Authority admin1 = new Authority("ROLE_ADMIN");
        Authority admin2 = new Authority("ROLE_ADMIN");
        Authority user = new Authority("ROLE_USER");

        Set<Authority> authorities = new HashSet<>();
        authorities.add(admin1);
        authorities.add(admin2);
        authorities.add(user);
        assertThat(authorities).hasSize(2);
    }
}
