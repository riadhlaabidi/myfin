package tn.riadh.myfin.product.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import org.jmolecules.ddd.types.Entity;

public class SellableForm implements Entity<Product, SellableFormId> {

    private final SellableFormId id;
    private final ProductId productId;
    private final FormLabel formLabel;
    private final BigDecimal conversionFactor;
    private final Optional<Barcode> barcode;

    private SellableForm(SellableFormId id, ProductId productId, FormLabel formLabel, BigDecimal conversionFactor,
            Optional<Barcode> barcode) {
        Objects.requireNonNull(id, "SellableFormId cannot be null");
        Objects.requireNonNull(productId, "ProductId cannot be null");
        Objects.requireNonNull(conversionFactor, "conversionFactor cannot be null");
        Objects.requireNonNull(barcode, "Barcode optional cannot be null");

        if (conversionFactor.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException("conversionFactor should be greater than 0");
        }

        this.id = id;
        this.productId = productId;
        this.formLabel = formLabel;
        this.conversionFactor = conversionFactor;
        this.barcode = barcode;
    }

    public static SellableForm create(ProductId productId, FormLabel formLabel, BigDecimal conversionFactor,
            Optional<Barcode> barcode) {
        return new SellableForm(SellableFormId.generate(), productId, formLabel, conversionFactor, barcode);
    }

    @Override
    public SellableFormId getId() {
        return id;
    }

    public ProductId productId() {
        return productId;
    }

    public FormLabel formLabel() {
        return formLabel;
    }

    public BigDecimal conversionFactor() {
        return conversionFactor;
    }

    public Optional<Barcode> barcode() {
        return barcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SellableForm)) {
            return false;
        }
        SellableForm other = (SellableForm) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
