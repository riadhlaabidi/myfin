package tn.riadh.myfin.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.ProductCategory;
import tn.riadh.myfin.repository.ProductCategoryRepository;

public class ProductCategoryJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static ProductCategory createProductCategory() {
        ProductCategory pc = new ProductCategory();
        pc.setName("category 1");
        return pc;
    }

    @Test
    public void shouldAddProductCategoryWhenSavedToDatabase() {
        ProductCategory pc = createProductCategory();
        int countBeforeInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, "product_categories");
        ProductCategory saved = productCategoryRepository.save(pc);
        assertThat(saved.getId()).isNotNull();
        int countAfterInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, "product_categories");
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
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
}
