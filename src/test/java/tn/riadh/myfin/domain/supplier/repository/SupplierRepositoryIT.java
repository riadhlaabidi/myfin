package tn.riadh.myfin.domain.supplier.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.supplier.Supplier;

public class SupplierRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private SupplierRepository supplierRepository;

    static Supplier createSupplier() {
        return new Supplier()
                .withName("supplier")
                .withAddress("address")
                .withPhoneNumber("999999999")
                .withTin("0099/0099/0099");
    }

    @Test
    @Transactional
    public void shouldAddSupplierWhenSavedToDatabase() {
        Supplier supplier = createSupplier();
        long countBeforeInsert = supplierRepository.count();
        Supplier saved = supplierRepository.save(supplier);
        assertThat(saved.getId()).isNotNull();
        long countAfterInsert = supplierRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
    @Transactional
    public void shouldReturnSupplierWhenIdExists() {
        Supplier supplier = createSupplier();
        Long id = supplierRepository.save(supplier).getId();
        Optional<Supplier> found = supplierRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
    public void shouldNotReturnSupplierWhenIdDoesNotExist() {
        Optional<Supplier> supplier = supplierRepository.findById(99999L);
        assertThat(supplier.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnTrueWhenIdExists() {
        Supplier supplier = createSupplier();
        Long id = supplierRepository.save(supplier).getId();
        assertThat(supplierRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(supplierRepository.existsById(99999L)).isFalse();
    }

    @Test
    @Transactional
    public void shouldReturnCorrectCount() {
        long countBeforeInsert = supplierRepository.count();
        supplierRepository.save(createSupplier());
        long countAfterInsert = supplierRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }
}
