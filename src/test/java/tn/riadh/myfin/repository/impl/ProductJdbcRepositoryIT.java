package tn.riadh.myfin.repository.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.Product;
import tn.riadh.myfin.domain.ProductCategory;
import tn.riadh.myfin.repository.ProductRepository;

public class ProductJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    static Product createProduct() {
        Product p = new Product();
        p.setName("Product1");
        p.setImageUrl("Image");
        p.setCategory(new ProductCategory());
        p.getCategory().setId(1L);
        return p;
    }

    @Test
    public void shouldAddProductWhenSavedToDatabase() {
        Product product = createProduct();
        int countBeforeInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, "products");
        Product saved = productRepository.save(product);
        assertThat(saved.getId()).isNotNull();
        int countAfterInsert = JdbcTestUtils.countRowsInTable(jdbcTemplate, "products");
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
    public void shouldReturnProductWhenIdExists() {
        Product product = createProduct();
        Long id = productRepository.save(product).getId();
        Optional<Product> found = productRepository.findById(id);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getId()).isEqualTo(id);
    }

    @Test
    public void shouldNotReturnProductWhenIdDoesNotExist() {
        Optional<Product> product = productRepository.findById(999L);
        assertThat(product.isEmpty()).isTrue();
    }
}
