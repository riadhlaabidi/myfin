package tn.riadh.myfin.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.InventoryItem;

/**
 * Repository interface for performing persistence operations on
 * {@link InventoryItem} entities.
 * <p>
 * Defines the contract for saving and searching inventory records.
 * Actual data access behavior is provided by the implementing class.
 * </p>
 */
public interface InventoryItemRepository {

    /**
     * Saves an inventory item.
     * 
     * @param iventoryItem the inventory item to persist
     * @return the persisted inventory item
     */
    InventoryItem save(InventoryItem inventoryItem);

    /**
     * Finds an iventory item by its identifier.
     * 
     * @param id the inventory item identifier
     * @return an {@link Optional} containing the inventory item if found,
     *         otherwise empty
     */
    Optional<InventoryItem> findById(Long id);

    /**
     * Finds an iventory item by its product identifier.
     * 
     * @param prodcutId the inventory item's product identifier
     * @return an {@link Optional} containing the inventory item if found,
     *         otherwise empty
     */
    Optional<InventoryItem> findByProductId(Long productId);

    /**
     * Checks if an iventory item exists by its identifier.
     * 
     * @param id the inventory item identifier
     * @return {@code true} if the inventory item exists, otherwise {@code false}
     */
    boolean existsById(Long id);

    /**
     * Counts the number of inventory items in the database.
     * 
     * @return the number of inventory items
     */
    long count();
}
