package tn.riadh.myfin.infrastructure.persistence.jdbc.supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supplier.repository.SupplierRepository;

@Profile("jdbc")
@Transactional
public class SupplierJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void shouldAddSupplierWhenSavedToDatabase() {
        Supplier supplier = createSupplier();
        long countBefore = countRowsInTable(jdbcTemplate, "suppliers");
        Supplier saved = supplierRepository.save(supplier);
        assertThat(saved.getId()).isNotNull();
        long countAfter = countRowsInTable(jdbcTemplate, "suppliers");
        assertThat(countAfter).isEqualTo(countBefore + 1);
    }

    @Test
    public void shouldReturnSupplierWhenIdExists() {
        Supplier supplier = createSupplier();
        Long id = supplierRepository.save(supplier).getId();
        Optional<Supplier> found = supplierRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
        assertThat(found.get().getName()).isEqualTo(supplier.getName());
        assertThat(found.get().getAddress()).isEqualTo(supplier.getAddress());
        assertThat(found.get().getPhoneNumber()).isEqualTo(supplier.getPhoneNumber());
        assertThat(found.get().getTin()).isEqualTo(supplier.getTin());
    }

    @Test
    public void shouldNotReturnSupplierWhenIdDoesNotExist() {
        Optional<Supplier> supplier = supplierRepository.findById(99999L);
        assertThat(supplier.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenIdExists() {
        Supplier supplier = createSupplier();
        Long id = supplierRepository.save(supplier).getId();
        assertThat(supplierRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(supplierRepository.existsById(99999L)).isFalse();
    }

    private Supplier createSupplier() {
        return new Supplier()
                .withName("supplier")
                .withAddress("address")
                .withPhoneNumber("999999999")
                .withTin("0099/0099/0099");
    }
}
