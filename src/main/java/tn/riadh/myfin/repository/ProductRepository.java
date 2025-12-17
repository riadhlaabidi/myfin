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
public interface ProductRepository extends CrudRepository<Product, Long> {
    /**
     * Finds a product by its barcode.
     * 
     * @param barcode the product's barcode
     * @return an {@link Optional} containing the product if found, empty otherwise
     */
    Optional<Product> findByBarcode(String barcode);
}
