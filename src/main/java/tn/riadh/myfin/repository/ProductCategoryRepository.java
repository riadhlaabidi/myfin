package tn.riadh.myfin.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.ProductCategory;

/**
 * Product category repository interface.
 */
public interface ProductCategoryRepository {

    /**
     * Saves a new or update a ProductCategory.
     * 
     * @param productCategory The product category object to save
     * @return The saved {@link ProductCategory} object
     */
    public ProductCategory save(ProductCategory productCategory);

    /**
     * Finds and returns an optional of a {@link ProductCategory} by its id.
     * 
     * @param id The id of the product category to find
     * @return a {@link ProductCategory} wrapped in an Optional
     */
    public Optional<ProductCategory> findById(Long id);

}
