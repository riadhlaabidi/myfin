package tn.riadh.myfin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.InventoryItem;
import tn.riadh.myfin.domain.Product;

public class InventoryItemRepositoryIT extends AbstractIntegrationTest {

    private static final long SAVED_PRODUCT_ID = 1L;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    static InventoryItem createInventoryItem() {
        Product product = new Product();
        product.setId(SAVED_PRODUCT_ID);
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setProduct(product);
        inventoryItem.setUnits(1);
        return inventoryItem;
    }

    @Test
    @Transactional
    public void shouldAddInventoryItemWhenSavedToDatabase() {
        long countBeforeInsert = inventoryItemRepository.count();
        InventoryItem inventoryItem = createInventoryItem();
        InventoryItem saved = inventoryItemRepository.save(inventoryItem);
        assertThat(saved.getId()).isNotNull();
        long countAfterInsert = inventoryItemRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
    @Transactional
    public void shouldReturnInventoryItemWhenIdExists() {
        InventoryItem inventoryItem = createInventoryItem();
        Long id = inventoryItemRepository.save(inventoryItem).getId();
        Optional<InventoryItem> found = inventoryItemRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
    public void shouldNotReturnInventoryItemWhenIdDoesNotExist() {
        Optional<InventoryItem> iventoryItem = inventoryItemRepository.findById(99999L);
        assertThat(iventoryItem.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnInvetoryItemWhenProductIdExists() {
        InventoryItem inventoryItem = createInventoryItem();
        inventoryItemRepository.save(inventoryItem);
        Optional<InventoryItem> found = inventoryItemRepository.findByProductId(SAVED_PRODUCT_ID);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getProduct().getId()).isEqualTo(SAVED_PRODUCT_ID);
    }

    @Test
    public void shouldNotReturnInventoryItemWhenProductIdDoesNotExist() {
        Optional<InventoryItem> found = inventoryItemRepository.findByProductId(99999L);
        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnCorrectCount() {
        long countBeforeInsert = inventoryItemRepository.count();
        InventoryItem inventoryItem = createInventoryItem();
        inventoryItemRepository.save(inventoryItem);
        long countAfterInsert = inventoryItemRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }
}
