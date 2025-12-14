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
public interface ProductCategoryRepository {

    /**
     * Saves the given product category.
     * 
     * @param productCategory the product category to persist
     * @return the persisted product category
     */
    ProductCategory save(ProductCategory productCategory);

    /**
     * Finds a product category by its identifier.
     * 
     * @param id the product category identifier
     * @return an {@code Optional} containing the product category if found,
     *         otherwise empty
     */
    Optional<ProductCategory> findById(Long id);

    /**
     * Checks if a product category exists by its identifier.
     * 
     * @param id the product category identifier
     * @return {@code true} if the product category exists, otherwise {@code false}
     */
    boolean existsById(Long id);

    /**
     * Counts the number of product categories in the database
     * 
     * @return the number of product categories
     */
    long count();
}
