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
public interface InventoryItemRepository extends CrudRepository<InventoryItem, Long> {

    /**
     * Finds an iventory item by its product identifier.
     * 
     * @param prodcutId the inventory item's product identifier
     * @return an {@link Optional} containing the inventory item if found,
     *         otherwise empty
     */
    Optional<InventoryItem> findByProductId(Long productId);
}
