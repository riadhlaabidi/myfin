package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.application.monetary.MonetaryFactory;
import tn.riadh.myfin.domain.common.MonetaryAmount;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;
import tn.riadh.myfin.domain.supply.repository.SupplyRepository;
import tn.riadh.myfin.infrastructure.context.MonetaryContext;
import tn.riadh.myfin.support.JdbcTestData;

@Profile("jdbc")
@Transactional
public class SupplyJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private JdbcTestData jdbcTestData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private MonetaryFactory monetaryFactory;

    @BeforeAll
    static void setUp() {
        MonetaryContext.setCurrency(Currency.getInstance("TND"));
    }

    @Test
    public void shouldAddSupplyWithItemsWhenSavedToDatabase() {
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
    }

    @Test
    public void shouldRetrieveSupplyWithItemsFromDatabase() {
        Supply supply = createSupplyWithItems();
        int itemsSize = supply.getSupplyItems().size();
        Long supplyId = supplyRepository.save(supply).getId();

        Optional<Supply> found = supplyRepository.findByIdWithSupplyItems(supply.getId());
        assertThat(found.isPresent()).isTrue();
        Supply s = found.get();
        assertThat(s.getSupplier().getId()).isEqualTo(supply.getSupplier().getId());
        assertThat(s.getInvoiceNumber()).isEqualTo(supply.getInvoiceNumber());
        assertThat(s.getSupplyDate()).isEqualTo(supply.getSupplyDate());
        assertThat(s.getTotal()).isEqualTo(supply.getTotal());

        List<SupplyItem> retrievedItems = s.getSupplyItems();
        assertThat(retrievedItems).hasSize(itemsSize);
        assertThat(retrievedItems).extracting(item -> item.getId()).doesNotContainNull();
        assertThat(retrievedItems).extracting(item -> item.getSupply().getId())
                .allSatisfy(si -> assertThat(si).isEqualTo(supplyId));

        List<Integer> expectedUnits = supply.getSupplyItems().stream().map(SupplyItem::getUnits).toList();
        assertThat(retrievedItems)
                .extracting(SupplyItem::getUnits)
                .containsExactlyInAnyOrderElementsOf(expectedUnits);

        List<MonetaryAmount> expectedSubtotals = supply.getSupplyItems().stream().map(SupplyItem::getSubtotal).toList();
        assertThat(retrievedItems)
                .extracting(SupplyItem::getSubtotal)
                .containsExactlyInAnyOrderElementsOf(expectedSubtotals);

        List<Long> expectedProductIds = supply.getSupplyItems()
                .stream()
                .map(item -> item.getProduct().getId())
                .toList();
        assertThat(retrievedItems)
                .extracting(item -> item.getProduct().getId())
                .containsExactlyInAnyOrderElementsOf(expectedProductIds);
    }

    @Test
    public void shouldReturnSupplyWhenIdExists() {
        Supply supply = createSupply();
        Long id = supplyRepository.save(supply).getId();
        Optional<Supply> found = supplyRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
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
    public void shouldReturnTrueWhenIdExists() {
        Supply supply = createSupply();
        Long id = supplyRepository.save(supply).getId();
        assertThat(supplyRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(supplyRepository.existsById(99999L)).isFalse();
    }

    private Supply createSupply() {
        Long supplierId = jdbcTestData.supplier();
        return new Supply()
                .withSupplier(new Supplier().id(supplierId))
                .withInvoiceNumber("99999999")
                .withSupplyDate(Instant.parse("2025-12-22T15:13:00Z"))
                .withSupplyItems(new ArrayList<>())
                .withTotal(monetaryFactory.amount(new BigDecimal("455.33")));
    }

    private Supply createSupplyWithItems() {
        Supply supply = createSupply();

        Long categoryId = jdbcTestData.productCategory();
        Long product1Id = jdbcTestData.product(categoryId);
        Long product2Id = jdbcTestData.product(categoryId);

        SupplyItem item1 = new SupplyItem()
                .withProduct(new Product().id(product1Id))
                .withUnits(4)
                .withSubtotal(monetaryFactory.amount(new BigDecimal("123.821")));
        SupplyItem item2 = new SupplyItem()
                .withProduct(new Product().id(product2Id))
                .withUnits(4)
                .withSubtotal(monetaryFactory.amount(new BigDecimal("100.887")));

        supply.addSupplyItem(item1);
        supply.addSupplyItem(item2);
        return supply;
    }
}
