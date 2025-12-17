package tn.riadh.myfin.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.ProductCategory;

/**
 * Repository interface for performing persistence operations on
 * {@link ProductCategory} entities.
 * <p>
 * Defines the contract for persisting and retrieving product categories.
 * Implementations handle the actual data access logic.
 * </p>
 */
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
