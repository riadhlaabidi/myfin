package tn.riadh.myfin.domain.common;

/**
 * Base class for all domain entities in the application.
 *
 * <p>
 * Provides a standard {@code id} property along with consistent
 * implementations of {@link #equals(Object)} and {@link #hashCode()}
 * based on the entity identifier. Entities are considered equal if
 * they are of the same class and share a non-null identifier.
 * </p>
 *
 * <p>
 * This class is intended to be extended by persistent entities to ensure
 * uniform identity handling throughout the domain model.
 * </p>
 */
public class AbstractEntity {
    private Long id;

    /**
     * Returns the unique identifier of the entity.
     *
     * @return the entity identifier, or {@code null} if not yet assigned
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id the identifier to assign
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Computes the hash code of the entity.
     * <p>
     * If the identifier is non-null, its hash code is returned.
     * Otherwise, the hash code of the entity's class is used.
     * </p>
     *
     * @return the hash code value for this entity
     */
    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        }
        return getClass().hashCode();
    }

    /**
     * Indicates whether this entity is equal to another object.
     * <p>
     * Two entities are considered equal if they are of the same class
     * and both have a non-null identifier that is equal.
     * </p>
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractEntity o = (AbstractEntity) obj;
        return id != null && id.equals(o.id);
    }
}
