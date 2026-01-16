package tn.riadh.myfin.domain.user;

import java.util.Objects;

/**
 * Represents a security authority assigned to a user.
 *
 * <p>
 * An {@code Authority} models a single permission or role (for example,
 * {@code ROLE_ADMIN}, {@code ROLE_USER}) as used by Spring Security.
 * The authority is identified solely by its {@code name}.
 * </p>
 *
 * <p>
 * Equality and hash code are based exclusively on the authority name,
 * making two {@code Authority} instances with the same name equal.
 * </p>
 */
public class Authority {

    private String name;

    /**
     * Creates a new {@code Authority} with the given name.
     * <p>
     * The name uniquely identifies the authority and must not be {@code null}.
     * Passing a {@code null} value indicates an invalid domain state and will
     * result in an exception.
     * </p>
     *
     * @param name the authority name
     * @throws NullPointerException if {@code name} is {@code null}
     */
    public Authority(String name) {
        this.name = Objects.requireNonNull(name, "authority name must not be null");
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Authority)) {
            return false;
        }
        Authority authority = (Authority) obj;
        return Objects.equals(this.name, authority.name);
    }

    @Override
    public String toString() {
        return "Authority{"
                + "name=" + name
                + "}";
    }
}
