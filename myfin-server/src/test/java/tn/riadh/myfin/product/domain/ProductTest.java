package tn.riadh.myfin.product.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.util.ArrayList;
import java.util.Collections;
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
    public void shouldAddSellableFormCorrectly() {
        List<SellableForm> sellableForms = List.of(
                SellableForm.create(FormLabel.PACK, 1, Barcode.from("111")));
        Product product = Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms);

        assertThatNoException().isThrownBy(() -> product.addSellableForm(FormLabel.PACK, 5, Barcode.from("222")));
        assertThat(product.sellableForms()).hasSize(2);
        assertThat(product.sellableForms().get(1).formLabel()).isEqualTo(FormLabel.PACK);
        assertThat(product.sellableForms().get(1).quantity().getAsInt()).isEqualTo(5);
        assertThat(product.sellableForms().get(1).barcode().get()).isEqualTo(Barcode.from("222"));

        assertThatNoException().isThrownBy(() -> product.addSellableForm(FormLabel.PACK, 10, Barcode.from("333")));
        assertThat(product.sellableForms()).hasSize(3);
        assertThat(product.sellableForms().getLast().formLabel()).isEqualTo(FormLabel.PACK);
        assertThat(product.sellableForms().getLast().quantity().getAsInt()).isEqualTo(10);
        assertThat(product.sellableForms().getLast().barcode().get()).isEqualTo(Barcode.from("333"));
    }

    @Test
    public void shouldThrowWhenAddingASellableFormWithAnAlreadyExistingBarcode() {
        List<SellableForm> sellableForms = List.of(
                SellableForm.create(FormLabel.PACK, 1, Barcode.from("111")));
        Product product = Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms);
        assertThatExceptionOfType(BarcodeAlreadyExistsException.class)
                .isThrownBy(() -> product.addSellableForm(FormLabel.SAC, 2, Barcode.from("111")));
    }

    @Test
    public void shouldThrowWhenAddingAnExistingSellableForm() {
        List<SellableForm> sellableForms = List.of(
                SellableForm.create(FormLabel.PACK, 1, Barcode.from("111")));
        Product product = Product.create("Product", CategoryId.generate(), UnitType.PIECE, sellableForms);
        assertThatExceptionOfType(SellableFormAlreadyExistsException.class)
                .isThrownBy(() -> product.addSellableForm(FormLabel.PACK, 1, Barcode.from("111")));
    }
}
