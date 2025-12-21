package tn.riadh.myfin.infrastructure.persistence.jdbc.inventory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.inventory.InventoryItem;
import tn.riadh.myfin.domain.inventory.repository.InventoryItemRepository;
import tn.riadh.myfin.domain.product.Product;

@Profile("jdbc")
public class InventoryItemJdbcRepositoryIT extends AbstractIntegrationTest {

    private static final long SAVED_PRODUCT_ID = 1L;

    static InventoryItem createInventoryItem() {
        return new InventoryItem()
                .withProduct(new Product().id(SAVED_PRODUCT_ID))
                .withUnits(1);
    }

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    @Transactional
    public void shouldAddInventoryItemWhenSavedToDatabase() {
        long countBefore = countRowsInTable(jdbcTemplate, "inventory_items");
        InventoryItem inventoryItem = createInventoryItem();
        InventoryItem saved = inventoryItemRepository.save(inventoryItem);
        assertThat(saved.getId()).isNotNull();
        long countAfter = countRowsInTable(jdbcTemplate, "inventory_items");
        assertThat(countAfter).isEqualTo(countBefore + 1);
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
}
