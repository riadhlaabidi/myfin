package tn.riadh.myfin.product.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tn.riadh.myfin.shared.quantity.UnitOfMesure;

class ProductServiceTest {

    private InMemorySellableFormUniquenessChecker checker;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        checker = new InMemorySellableFormUniquenessChecker();
        productService = new ProductService(checker);
    }

    @Test
    void shouldCreateProductWhenBarcodeIsUnique() {
        assertThatNoException().isThrownBy(() -> {
            productService.create("Product", CategoryId.generate(), UnitOfMesure.PIECE,
                    null, Barcode.from("1111"), null);
        });
    }

    @Test
    void shouldCreateProductWhenPluCodeIsUnique() {
        assertThatNoException().isThrownBy(() -> {
            productService.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, null, null, PluCode.of(1234));
        });
    }

    @Test
    void shouldThrowWhenCreatingProductWhenBarcodeAlreadyExists() {
        checker.withUsedBarcode(Barcode.from("1234"));

        assertThatExceptionOfType(BarcodeAlreadyExistsException.class).isThrownBy(() -> {
            productService.create("Product", CategoryId.generate(), UnitOfMesure.PIECE,
                    null, Barcode.from("1234"), null);
        });
    }

    @Test
    void shouldThrowWhenCreatingProductWhenPluCodeAlreadyExists() {
        checker.withUsedPluCode(PluCode.of(555));

        assertThatExceptionOfType(PluCodeAlreadyExistsException.class).isThrownBy(() -> {
            productService.create("Product", CategoryId.generate(), UnitOfMesure.PIECE,
                    null, null, PluCode.of(555));
        });
    }

    // TODO: add tests for addSellableForm
}
