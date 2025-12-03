package tn.riadh.myfin.repository;

import tn.riadh.myfin.domain.Product;

import java.util.Optional;

/**
 * Product repository interface
 */
public interface ProductRepository {

    /**
     * Saves a new product to the database.
     * 
     * @param product The product object to save.
     * @return The save object with id set.
     */
    public Product save(Product product);

    /**
     * Finds and returns a product by its id, wrapped in an Optional object.
     * 
     * @param id The product id
     * @return Optional of a product
     */
    public Optional<Product> findById(Long id);
}
