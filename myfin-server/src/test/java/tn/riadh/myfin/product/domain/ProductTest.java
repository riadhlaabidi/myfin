package tn.riadh.myfin.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.shared.quantity.UnitOfMesure;

class ProductTest {

    @Test
    public void shouldActivateProductWhenCreated() {
        Product product = Product.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, FormLabel.PACK,
                Barcode.from("111"), null);
        assertThat(product.status()).isEqualTo(ProductStatus.ACTIVE);
    }

    @Test
    public void shouldAddBaseUnitSellableFormWhenCreated() {
        Product product = Product.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, FormLabel.PACK,
                Barcode.from("111"), null);

        assertThat(product.sellableForms()).hasSize(1);

        var form = product.sellableForms().getFirst();
        assertThat(form.baseUnitQuantity()).isEqualTo(1);
        assertThat(form.formLabel()).isPresent().hasValue(FormLabel.PACK);
        assertThat(form.barcode()).isPresent().hasValue(Barcode.from("111"));
        assertThat(form.plucode()).isNotPresent();
    }

    @Test
    public void shouldThrowWhenAddingSellableFormWithNoBarcodeAndPluCodeToWeighableProduct() {
        assertThatExceptionOfType(UnidentifiableSellableFormException.class)
                .isThrownBy(() -> Product.create("Product", CategoryId.generate(), UnitOfMesure.KILOGRAM, null, null,
                        null));
    }

    @Test
    public void shouldThrowWhenAddingSellableFormWithNoBarcodeAndPluCodeToCountableProducts() {
        assertThatExceptionOfType(UnidentifiableSellableFormException.class)
                .isThrownBy(() -> Product.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, null,
                        null, null));

        Product product = Product.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, null,
                Barcode.from("111"), null);
        assertThatExceptionOfType(UnidentifiableSellableFormException.class)
                .isThrownBy(() -> product.addSellableForm(null, 2, null, null));
    }

    @Test
    public void shouldThrowWhenAddingSellableFormToWeighableProduct() {
        Product product = Product.create("Product", CategoryId.generate(), UnitOfMesure.KILOGRAM, null,
                Barcode.from("111"), null);

        assertThatExceptionOfType(IncompatibleSellableFormException.class)
                .isThrownBy(() -> product.addSellableForm(null, 2, Barcode.from("222"), null));
    }

    @Test
    public void shouldThrowWhenAddingSellableFormWithExistantBaseUnitQuantity() {
        Product product = Product.create("Product", CategoryId.generate(), UnitOfMesure.PIECE, FormLabel.PACK,
                Barcode.from("111"), null);

        assertThatExceptionOfType(SellableFormAlreadyExistsException.class)
                .isThrownBy(() -> product.addSellableForm(null, 1, Barcode.from("222"), null));
    }
}
