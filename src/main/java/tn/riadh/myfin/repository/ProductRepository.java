package tn.riadh.myfin.repository;

import tn.riadh.myfin.domain.Product;

import java.util.Optional;

/**
 * Repository interface for performing persistence operations on {@link Product}
 * entities.
 * <p>
 * Defines the contract for saving products and retrieving them by identifier.
 * Actual data access behavior is provided by the implementing class.
 * </p>
 */
public interface ProductRepository {

    /**
     * Saves the given product.
     *
     * @param product the product to persist
     * @return the persisted product
     */
    Product save(Product product);

    /**
     * Finds a product by its identifier.
     *
     * @param id the product identifier
     * @return an {@code Optional} containing the product if found, otherwise empty
     */
    Optional<Product> findById(Long id);

    /**
     * Finds a product by its barcode.
     * 
     * @param barcode the product's barcode
     * @return an {@link Optional} containing the product if found, empty otherwise
     */
    Optional<Product> findByBarcode(String barcode);

    /**
     * Checks if a product exists by its identifier.
     * 
     * @param id the product identifier
     * @return {@code true} if the product exists, otherwise {@code false}
     */
    boolean existsById(Long id);

    /**
     * Counts the number of products in the database
     * 
     * @return the number of products
     */
    long count();
}
