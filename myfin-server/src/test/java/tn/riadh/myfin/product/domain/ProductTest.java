package tn.riadh.myfin.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.shared.quantity.UnitType;

public class ProductTest {

    @Test
    public void shouldActivateProductWhenCreated() {
        List<SellableForm> sellableForms = List.of(SellableForm.create(null, 1, Barcode.from("111")));
        Product product = Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms);
        assertThat(product.status()).isEqualTo(ProductStatus.ACTIVE);
    }

    @Test
    public void shouldThrowWhenCreatingAProductWithNoSellableForms() {
        assertThatExceptionOfType(ProductWithNoSellableFormsException.class)
                .isThrownBy(() -> Product.create("Product", CategoryId.generate(), UnitType.PIECE, new ArrayList<>()));
    }

    @Test
    public void shouldThrowWhenCreatedWithIncompatibleSellableFormQuantityForWeighableProducts() {
        List<SellableForm> sellableForms = List.of(SellableForm.create(null, 1, Barcode.from("111")));
        assertThatExceptionOfType(IncompatibleSellableFormException.class)
                .isThrownBy(() -> Product.create("Product", CategoryId.generate(), UnitType.KILOGRAM, sellableForms));
    }

    @Test
    public void shouldThrowWhenCreatedWithIncompatibleSellableFormQuantityForCountableProducts() {
        List<SellableForm> sellableForms = List.of(SellableForm.create(null, null, Barcode.from("111")));
        assertThatExceptionOfType(IncompatibleSellableFormException.class)
                .isThrownBy(() -> Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms));
    }

    @Test
    public void shouldThrowWhenAddingAnExistingSellableForm() {
        List<SellableForm> sellableForms = List.of(
                SellableForm.create(FormLabel.PACK, 1, Barcode.from("111")));
        Product product = Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms);
        assertThatExceptionOfType(SellableFormAlreadyExistException.class)
                .isThrownBy(() -> product.addSellableForm(FormLabel.PACK, 1, Barcode.from("111")));
    }
}
