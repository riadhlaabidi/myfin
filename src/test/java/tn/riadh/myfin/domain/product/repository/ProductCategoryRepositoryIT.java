package tn.riadh.myfin.domain.product.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.AbstractIntegrationTest;
import tn.riadh.myfin.domain.product.ProductCategory;

public class ProductCategoryRepositoryIT extends AbstractIntegrationTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    static ProductCategory createProductCategory() {
        return new ProductCategory().withName("category");
    }

    @Test
    @Transactional
    public void shouldAddProductCategoryWhenSavedToDatabase() {
        ProductCategory pc = createProductCategory();
        long countBeforeInsert = productCategoryRepository.count();
        ProductCategory saved = productCategoryRepository.save(pc);
        assertThat(saved.getId()).isNotNull();
        long countAfterInsert = productCategoryRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
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

    @Test
    @Transactional
    public void shouldReturnCorrectCount() {
        long countBeforeInsert = productCategoryRepository.count();
        ProductCategory productCategory = createProductCategory();
        productCategoryRepository.save(productCategory);
        long countAfterInsert = productCategoryRepository.count();
        assertThat(countAfterInsert).isEqualTo(countBeforeInsert + 1);
    }
}
