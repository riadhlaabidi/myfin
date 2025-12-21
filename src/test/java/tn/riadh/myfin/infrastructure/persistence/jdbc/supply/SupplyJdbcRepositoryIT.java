package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;
import tn.riadh.myfin.domain.supply.repository.SupplyRepository;

@Profile("jdbc")
public class SupplyJdbcRepositoryIT extends AbstractIntegrationTest {

    private static final Long SAVED_SUPPLIER_ID = 1L;

    static Supply createSupply() {
        return new Supply()
                .withSupplier(new Supplier().id(SAVED_SUPPLIER_ID))
                .withInvoiceNumber("999999")
                .withSupplyDate(Instant.now())
                .withSupplyItems(new ArrayList<>())
                .withTotal(455.00);
    }

    static Supply createSupplyWithItems() {
        Supply supply = new Supply()
                .withSupplier(new Supplier().id(SAVED_SUPPLIER_ID))
                .withInvoiceNumber("999999")
                .withSupplyDate(Instant.now().truncatedTo(ChronoUnit.MICROS))
                .withTotal(455.00);
        SupplyItem item1 = new SupplyItem()
                .withProduct(new Product().id(1L))
                .withUnits(4)
                .withSubtotal(100);
        SupplyItem item2 = new SupplyItem()
                .withProduct(new Product().id(2L))
                .withUnits(4)
                .withSubtotal(200);
        supply.addSupplyItem(item1);
        supply.addSupplyItem(item2);
        return supply;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SupplyRepository supplyRepository;

    @Test
    @Transactional
    public void shouldAddSupplyWhenSavedToDatabase() {
        Supply supply = createSupplyWithItems();
        List<SupplyItem> items = supply.getSupplyItems();
        int itemsSize = items.size();

        long supplyCountBefore = countRowsInTable(jdbcTemplate, "supplies");
        long supplyItemsCountBefore = countRowsInTable(jdbcTemplate, "supply_items");

        Long supplyId = supplyRepository.save(supply).getId();
        assertThat(supplyId).isNotNull();

        long supplyCountAfter = countRowsInTable(jdbcTemplate, "supplies");
        long supplyItemsCountAfter = countRowsInTable(jdbcTemplate, "supply_items");
        long supplyItemsCount = countRowsInTableWhere(jdbcTemplate, "supply_items", "supply_id = " + supplyId);

        assertThat(supplyCountAfter).isEqualTo(supplyCountBefore + 1);
        assertThat(supplyItemsCountAfter).isEqualTo(supplyItemsCountBefore + itemsSize);
        assertThat(supplyItemsCount).isEqualTo(itemsSize);

        Optional<Supply> found = supplyRepository.findByIdWithSupplyItems(supplyId);
        assertThat(found.isPresent()).isTrue();
        Supply s = found.get();
        assertThat(s.getSupplier().getId()).isEqualTo(supply.getSupplier().getId());
        assertThat(s.getInvoiceNumber()).isEqualTo(supply.getInvoiceNumber());
        assertThat(s.getSupplyDate()).isEqualTo(supply.getSupplyDate());
        assertThat(s.getTotal()).isEqualTo(supply.getTotal());

        List<SupplyItem> retrievedItems = s.getSupplyItems();
        assertThat(retrievedItems).hasSize(itemsSize);
        for (int i = 0; i < itemsSize; i++) {
            assertThat(retrievedItems.get(i).getId()).isNotNull();
            assertThat(retrievedItems.get(i).getSupply().getId()).isEqualTo(supplyId);
            assertThat(retrievedItems.get(i).getProduct().getId()).isEqualTo(items.get(i).getProduct().getId());
            assertThat(retrievedItems.get(i).getUnits()).isEqualTo(items.get(i).getUnits());
            assertThat(retrievedItems.get(i).getSubtotal()).isEqualTo(items.get(i).getSubtotal());
        }
    }

    @Test
    @Transactional
    public void shouldReturnSupplyWhenIdExists() {
        Supply supply = createSupply();
        Long id = supplyRepository.save(supply).getId();
        Optional<Supply> found = supplyRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
    @Transactional
    public void shouldReturnSupplyWithSupplyItemsWhenIdExists() {
        Supply supply = createSupplyWithItems();
        int itemsSize = supply.getSupplyItems().size();
        Long supplyId = supplyRepository.save(supply).getId();

        Optional<Supply> found = supplyRepository.findByIdWithSupplyItems(supplyId);
        assertThat(found.isPresent()).isTrue();

        List<SupplyItem> items = found.get().getSupplyItems();
        assertThat(items).hasSize(itemsSize);
        for (SupplyItem item : items) {
            assertThat(item.getSupply().getId()).isEqualTo(supplyId);
        }
    }

    @Test
    public void shouldNotReturnSupplyWhenIdDoesNotExist() {
        Optional<Supply> supply = supplyRepository.findById(99999L);
        assertThat(supply.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnTrueWhenIdExists() {
        Supply supply = createSupply();
        Long id = supplyRepository.save(supply).getId();
        assertThat(supplyRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(supplyRepository.existsById(99999L)).isFalse();
    }
}
