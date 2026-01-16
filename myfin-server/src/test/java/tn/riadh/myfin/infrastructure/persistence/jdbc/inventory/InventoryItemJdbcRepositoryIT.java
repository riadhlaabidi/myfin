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
import tn.riadh.myfin.support.JdbcTestData;

@Profile("jdbc")
@Transactional
public class InventoryItemJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private JdbcTestData jdbcTestData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Test
    public void shouldAddInventoryItemWhenSavedToDatabase() {
        long countBefore = countRowsInTable(jdbcTemplate, "inventory_items");
        InventoryItem inventoryItem = createInventoryItem();
        InventoryItem saved = inventoryItemRepository.save(inventoryItem);
        assertThat(saved.getId()).isNotNull();
        long countAfter = countRowsInTable(jdbcTemplate, "inventory_items");
        assertThat(countAfter).isEqualTo(countBefore + 1);
    }

    @Test
    public void shouldReturnInventoryItemWhenIdExists() {
        InventoryItem inventoryItem = createInventoryItem();
        Long id = inventoryItemRepository.save(inventoryItem).getId();
        Optional<InventoryItem> found = inventoryItemRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
        assertThat(found.get().getProduct().getId()).isEqualTo(inventoryItem.getProduct().getId());
        assertThat(found.get().getUnits()).isEqualTo(inventoryItem.getUnits());
    }

    @Test
    public void shouldNotReturnInventoryItemWhenIdDoesNotExist() {
        Optional<InventoryItem> iventoryItem = inventoryItemRepository.findById(99999L);
        assertThat(iventoryItem.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnInvetoryItemWhenProductIdExists() {
        InventoryItem inventoryItem = createInventoryItem();
        inventoryItemRepository.save(inventoryItem);
        Optional<InventoryItem> found = inventoryItemRepository.findByProductId(inventoryItem.getProduct().getId());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getProduct().getId()).isEqualTo(inventoryItem.getProduct().getId());
    }

    @Test
    public void shouldNotReturnInventoryItemWhenProductIdDoesNotExist() {
        Optional<InventoryItem> found = inventoryItemRepository.findByProductId(99999L);
        assertThat(found.isEmpty()).isTrue();
    }

    private InventoryItem createInventoryItem() {
        Long categoryId = jdbcTestData.productCategory();
        Long productId = jdbcTestData.product(categoryId);
        return new InventoryItem()
                .withProduct(new Product().id(productId))
                .withUnits(1);
    }
}
