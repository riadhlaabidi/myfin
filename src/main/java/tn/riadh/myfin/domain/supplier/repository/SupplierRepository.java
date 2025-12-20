package tn.riadh.myfin.domain.supplier.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.supplier.Supplier;

/**
 * Repository interface for performing persistence operations on
 * {@link Supplier} entities.
 * <p>
 * Defines the contract for saving suppliers and retrieving them by identifier.
 * Actual data access behavior is provided by the implementing class.
 * </p>
 */
public interface SupplierRepository {
    /**
     * Saves the given {@link Supplier}.
     *
     * @param product the {@link Supplier} to persist
     * @return the persisted {@link Supplier}
     */
    Supplier save(Supplier product);

    /**
     * Finds a {@link Supplier} by its identifier.
     *
     * @param id the {@link Supplier} identifier
     * @return an {@code Optional} containing the {@link Supplier} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<Supplier> findById(Long id);

    /**
     * Checks if a {@link Supplier} exists by its identifier.
     * 
     * @param id the {@link Supplier} identifier
     * @return {@code true} if the {@link Supplier} exists, {@code false}
     *         otherwise
     * 
     */
    boolean existsById(Long id);

    /**
     * Counts the number of {@link Supplier} in the data store.
     * 
     * @return the number of {@link Supplier}
     */
    long count();
}
