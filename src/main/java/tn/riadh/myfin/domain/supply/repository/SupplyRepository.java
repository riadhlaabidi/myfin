package tn.riadh.myfin.domain.supply.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.supply.Supply;

public interface SupplyRepository {

    /**
     * Saves the given {@link Supply}.
     *
     * @param supply the {@link Supply} to persist
     * @return the persisted {@link Supply}
     */
    Supply save(Supply supply);

    /**
     * Finds a {@link Supply} by its identifier.
     *
     * @param id the {@link Supply} identifier
     * @return an {@code Optional} containing the {@link Supply} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<Supply> findById(Long id);

    /**
     * Finds a {@link Supply} aggregate by its identifier, including all
     * associated supply items.
     * <p>
     * This method is intended for use cases that require the complete supply
     * aggregate (for example, detailed views or business operations that depend
     * on supply items). Implementations are expected to load the supply and its
     * items in a single, consistent operation.
     * </p>
     *
     * @param id the supply identifier
     * @return an {@link Optional} containing the fully populated supply if it
     *         exists; {@link Optional#empty()} otherwise
     */
    Optional<Supply> findByIdWithSupplyItems(Long id);

    /**
     * Checks if a {@link Supply} exists by its identifier.
     * 
     * @param id the {@link Supply} identifier
     * @return {@code true} if the {@link Supply} exists, {@code false}
     *         otherwise
     * 
     */
    boolean existsById(Long id);

    /**
     * Counts the number of {@link Supply} in the data store.
     * 
     * @return the number of {@link Supply}
     */
    long count();
}
