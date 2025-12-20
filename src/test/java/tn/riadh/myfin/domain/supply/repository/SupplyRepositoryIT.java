package tn.riadh.myfin.domain.supply.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;

public class SupplyRepositoryIT extends AbstractIntegrationTest {

    private static final Long SAVED_SUPPLIER_ID = 1L;

    @Autowired
    private SupplyRepository supplyRepository;

    @Autowired
    private SupplyItemRepository supplyItemRepository;

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
                .withSupplyDate(Instant.now())
                .withTotal(455.00);
        SupplyItem item1 = new SupplyItem()
                .withSupply(supply)
                .withProduct(new Product().id(1L))
                .withUnits(4)
                .withSubtotal(100);
        SupplyItem item2 = new SupplyItem()
                .withSupply(supply)
                .withProduct(new Product().id(2L))
                .withUnits(4)
                .withSubtotal(200);
        supply.addSupplyItem(item1);
        supply.addSupplyItem(item2);
        return supply;
    }

    @Test
    @Transactional
    public void shouldAddSupplyWhenSavedToDatabase() {
        Supply supply = createSupply();
        long countBeforeInsert = supplyRepository.count();
        Supply saved = supplyRepository.save(supply);
        assertThat(saved.getId()).isNotNull();
        long countAfterInsert = supplyRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
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

    // TODO: Review the logic of saving a supply
    @Test
    @Transactional
    public void shouldReturnSupplyWithSupplyItemsWhenIdExists() {
        Supply supply = createSupplyWithItems();
        Supply saved = supplyRepository.save(supply);
        saved.getSupplyItems().forEach(item -> supplyItemRepository.save(item));
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getSupplyItems().getFirst().getId()).isNotNull();
        Optional<Supply> found = supplyRepository.findByIdWithSupplyItems(saved.getId());
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getSupplyItems()).hasSize(2);
        assertThat(found.get().getSupplyItems().getFirst().getSupply().getId()).isEqualTo(found.get().getId());
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

    @Test
    @Transactional
    public void shouldReturnCorrectCount() {
        long countBeforeInsert = supplyRepository.count();
        supplyRepository.save(createSupply());
        long countAfterInsert = supplyRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }
}
