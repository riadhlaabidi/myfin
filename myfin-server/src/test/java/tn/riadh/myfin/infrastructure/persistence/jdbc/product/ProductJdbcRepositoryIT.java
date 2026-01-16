package tn.riadh.myfin.infrastructure.persistence.jdbc.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.product.ProductCategory;
import tn.riadh.myfin.domain.product.repository.ProductRepository;
import tn.riadh.myfin.support.JdbcTestData;

@Profile("jdbc")
@Transactional
public class ProductJdbcRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private JdbcTestData jdbcTestData;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldAddProductWhenSavedToDatabase() {
        Product product = createProduct();
        long countBefore = countRowsInTable(jdbcTemplate, "products");
        Product saved = productRepository.save(product);
        assertThat(saved.getId()).isNotNull();
        long countAfter = countRowsInTable(jdbcTemplate, "products");
        assertThat(countAfter).isEqualTo(countBefore + 1);
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
        Optional<Product> product = productRepository.findById(99999L);
        assertThat(product.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnProductWhenBarcodeExists() {
        Product product = createProduct();
        String barcode = product.getBarcode();
        productRepository.save(product);
        Optional<Product> found = productRepository.findByBarcode(barcode);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getBarcode()).isEqualTo(barcode);
    }

    @Test
    public void shouldThrowExceptionWhenAddingProductWithAlreadyExistantBarcode() {
        Product product = createProduct();
        productRepository.save(product);
        assertThatThrownBy(() -> productRepository.save(product))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("unique");
    }

    @Test
    public void shouldNotReturnProductWhenBarcodeDoesNotExist() {
        Optional<Product> found = productRepository.findByBarcode(UUID.randomUUID().toString());
        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    public void shouldReturnTrueWhenIdExists() {
        Product p = createProduct();
        Long id = productRepository.save(p).getId();
        assertThat(productRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(productRepository.existsById(99999L)).isFalse();
    }

    private Product createProduct() {
        Long categoryId = jdbcTestData.productCategory();
        return new Product()
                .withName("product")
                .withBarcode(UUID.randomUUID().toString())
                .withImageUrl("image")
                .withCategory(new ProductCategory().id(categoryId));
    }
}
