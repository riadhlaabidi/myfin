package tn.riadh.myfin.domain.product.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.product.ProductCategory;

/**
 * Repository interface for performing persistence operations on
 * {@link ProductCategory} entities.
 * <p>
 * Defines the contract for persisting and retrieving product categories.
 * Implementations handle the actual data access logic.
 * </p>
 */
public interface ProductCategoryRepository {

    /**
     * Saves the given {@link ProductCategory}.
     *
     * @param productCategory the {@link ProductCategory} to persist
     * @return the persisted {@link ProductCategory}
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * Finds a {@link ProductCategory} by its identifier.
     *
     * @param id the {@link ProductCategory} identifier
     * @return an {@code Optional} containing the {@link ProductCategory} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<ProductCategory> findById(Long id);

    /**
     * Checks if a {@link ProductCategory} exists by its identifier.
     * 
     * @param id the {@link ProductCategory} identifier
     * @return {@code true} if the {@link ProductCategory} exists, {@code false}
     *         otherwise
     * 
     */
    boolean existsById(Long id);
}
