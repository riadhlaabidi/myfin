package tn.riadh.myfin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.Product;
import tn.riadh.myfin.domain.ProductCategory;

public class ProductRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    static Product createProduct() {
        ProductCategory category = new ProductCategory();
        category.setId(1L);
        Product p = new Product();
        p.setName("product");
        p.setBarcode(UUID.randomUUID().toString());
        p.setImageUrl("image");
        p.setCategory(category);
        return p;
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
