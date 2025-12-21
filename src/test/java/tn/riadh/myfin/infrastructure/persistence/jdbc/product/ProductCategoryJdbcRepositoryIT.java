package tn.riadh.myfin.infrastructure.persistence.jdbc.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.ProductCategory;
import tn.riadh.myfin.domain.product.repository.ProductCategoryRepository;

@Profile("jdbc")
public class ProductCategoryJdbcRepositoryIT extends AbstractIntegrationTest {

    static ProductCategory createProductCategory() {
        return new ProductCategory().withName("category");
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    @Transactional
    public void shouldAddProductCategoryWhenSavedToDatabase() {
        ProductCategory pc = createProductCategory();
        long countBefore = countRowsInTable(jdbcTemplate, "product_categories");
        ProductCategory saved = productCategoryRepository.save(pc);
        assertThat(saved.getId()).isNotNull();
        long countAfter = countRowsInTable(jdbcTemplate, "product_categories");
        assertThat(countAfter).isEqualTo(countBefore + 1);
    }

    @Test
    @Transactional
    public void shouldReturnProductCategoryWhenIdExists() {
        ProductCategory pc = createProductCategory();
        Long id = productCategoryRepository.save(pc).getId();
        Optional<ProductCategory> found = productCategoryRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
    public void shouldNotReturnProductCategoryWhenIdDoesNotExist() {
        Optional<ProductCategory> pc = productCategoryRepository.findById(999L);
        assertThat(pc.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnTrueWhenIdExists() {
        ProductCategory productCategory = createProductCategory();
        ProductCategory saved = productCategoryRepository.save(productCategory);
        boolean found = productCategoryRepository.existsById(saved.getId());
        assertThat(found).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        boolean found = productCategoryRepository.existsById(99999L);
        assertThat(found).isFalse();
    }
}
