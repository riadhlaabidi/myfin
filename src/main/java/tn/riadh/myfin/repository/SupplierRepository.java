package tn.riadh.myfin.repository;

import tn.riadh.myfin.domain.Supplier;

/**
 * Repository interface for performing persistence operations on
 * {@link Supplier} entities.
 * <p>
 * Defines the contract for saving suppliers and retrieving them by identifier.
 * Actual data access behavior is provided by the implementing class.
 * </p>
 */
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

}
