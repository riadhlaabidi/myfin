package tn.riadh.myfin.repository;

import java.util.Optional;

public interface CrudRepository<E, ID> {
    /**
     * Saves the given entity.
     *
     * @param entity the entity to persist
     * @return the persisted entity
     */
    E save(E entity);

    /**
     * Finds an entity by its identifier.
     *
     * @param id the entity identifier
     * @return an {@code Optional} containing the entity if found, otherwise empty
     */
    Optional<E> findById(ID id);

    /**
     * Checks if an entity exists by its identifier.
     * 
     * @param id the entity identifier
     * @return {@code true} if the entity exists, otherwise {@code false}
     */
    boolean existsById(ID id);

    /**
     * Counts the number of entities in the database
     * 
     * @return the number of entities
     */
    long count();
}
