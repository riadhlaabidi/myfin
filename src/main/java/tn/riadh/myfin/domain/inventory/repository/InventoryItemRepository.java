package tn.riadh.myfin.domain.inventory.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.inventory.InventoryItem;

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
     * Saves the given {@link InventoryItem}.
     *
     * @param inventoryItem the {@link InventoryItem} to persist
     * @return the persisted {@link InventoryItem}
     */
    InventoryItem save(InventoryItem inventoryItem);

    /**
     * Finds an {@link InventoryItem} by its identifier.
     *
     * @param id the {@link InventoryItem} identifier
     * @return an {@code Optional} containing the {@link InventoryItem} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<InventoryItem> findById(Long id);

    /**
     * Finds an {@link InventoryItem} by its product identifier.
     * 
     * @param prodcutId the {@link InventoryItem}'s product identifier
     * @return an {@link Optional} containing the {@link InventoryItem} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<InventoryItem> findByProductId(Long productId);

    /**
     * Checks if an {@link InventoryItem} exists by its identifier.
     * 
     * @param id the {@link InventoryItem} identifier
     * @return {@code true} if the {@link InventoryItem} exists, otherwise
     *         {@code false}
     */
    boolean existsById(Long id);
}
