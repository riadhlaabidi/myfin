package tn.riadh.myfin.domain.product.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.product.Product;

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
     * Saves the given {@link Product}.
     *
     * @param product the {@link Product} to persist
     * @return the persisted {@link Product}
     */
    Product save(Product product);

    /**
     * Finds a {@link Product} by its identifier.
     *
     * @param id the {@link Product} identifier
     * @return an {@code Optional} containing the {@link Product} if found,
     *         {@link Optional#empty()} otherwise
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
     * Checks if a {@link Product} exists by its identifier.
     * 
     * @param id the {@link Product} identifier
     * @return {@code true} if the {@link Product} exists, {@code false}
     *         otherwise
     * 
     */
    boolean existsById(Long id);
}
