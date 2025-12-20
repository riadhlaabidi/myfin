package tn.riadh.myfin.domain.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.product.ProductCategory;

public class ProductRepositoryIT extends AbstractIntegrationTest {

    private static final long SAVED_CATEGORY_ID = 1L;

    @Autowired
    private ProductRepository productRepository;

    static Product createProduct() {
        return new Product()
                .withName("product")
                .withBarcode(UUID.randomUUID().toString())
                .withImageUrl("image")
                .withCategory(new ProductCategory().id(SAVED_CATEGORY_ID));
    }

    @Test
    @Transactional
    public void shouldAddProductWhenSavedToDatabase() {
        Product product = createProduct();
        long countBeforeInsert = productRepository.count();
        Product saved = productRepository.save(product);
        assertThat(saved.getId()).isNotNull();
        long countAfterInsert = productRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }

    @Test
    @Transactional
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
    @Transactional
    public void shouldReturnProductWhenBarcodeExists() {
        Product product = createProduct();
        String barcode = product.getBarcode();
        productRepository.save(product);
        Optional<Product> found = productRepository.findByBarcode(barcode);
        assertThat(found.isPresent()).isTrue();
        assertThat(found.get().getBarcode()).isEqualTo(barcode);
    }

    @Test
    @Transactional
    public void shouldNotReturnProductWhenBarcodeDoesNotExist() {
        Optional<Product> found = productRepository.findByBarcode(UUID.randomUUID().toString());
        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    public void shouldReturnTrueWhenIdExists() {
        Product p = createProduct();
        Long id = productRepository.save(p).getId();
        assertThat(productRepository.existsById(id)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenIdDoesNotExist() {
        assertThat(productRepository.existsById(99999L)).isFalse();
    }

    @Test
    @Transactional
    public void shouldReturnCorrectCount() {
        long countBeforeInsert = productRepository.count();
        productRepository.save(createProduct());
        long countAfterInsert = productRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }
}
