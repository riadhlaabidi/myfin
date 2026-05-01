package tn.riadh.myfin.product.domain;

import java.util.Objects;
import java.util.Optional;

import org.jmolecules.ddd.types.Entity;

/**
 * A SellableForm represents a specific way a {@link Product} can be sold,
 * defined as a quantity of the product's base unit.
 * 
 * <p>
 * Examples:
 * <ul>
 * <li>A single can of "Soda" is a SellableForm with a quantity of 1 base unit,
 * identified by its own {@link Barcode}</li>
 * <li>A pack of 6 cans is another SellableForm with a quantity of 6 base units,
 * identified by a different {@link Barcode}</li>
 * </ul>
 *
 * <p>
 * Pricing is not part of the SellableForm, instead it is managed externally via
 * a {@link PriceRecord}, which references a SellableForm by its identity.
 *
 * <p>
 * A SellableForm of a Product is identified with a {@link Barcode} or a
 * {@link PluCode}. When neither of these identifier codes are provided, the
 * form is considered unidentifiable and creation throws an
 * {@link UnidentifiableSellableFormException}.
 * 
 * <p>
 * A SellableForm is an entity managed by the {@link Product} aggregate root.
 */
public class SellableForm implements Entity<Product, SellableFormId> {
    private final SellableFormId id;
    private final FormLabel formLabel;
    private final Integer baseUnitQuantity;
    private final Barcode barcode;
    private final PluCode plucode;

    private SellableForm(Builder builder) {
        Objects.requireNonNull(builder.id, "SellableFormId cannot be null");
        Objects.requireNonNull(builder.baseUnitQuantity, "baseUnitQuantity cannot be null");

        if (builder.barcode == null && builder.plucode == null) {
            throw new UnidentifiableSellableFormException();
        }

        if (builder.baseUnitQuantity < 1) {
            throw new IllegalArgumentException("baseUnitQuantity should be greater than 0");
        }

        this.id = builder.id;
        this.formLabel = builder.formLabel;
        this.baseUnitQuantity = builder.baseUnitQuantity;
        this.barcode = builder.barcode;
        this.plucode = builder.plucode;
    }

    @Override
    public SellableFormId getId() {
        return id;
    }

    public Optional<FormLabel> formLabel() {
        return Optional.ofNullable(formLabel);
    }

    public Integer baseUnitQuantity() {
        return baseUnitQuantity;
    }

    public Optional<Barcode> barcode() {
        return Optional.ofNullable(barcode);
    }

    public Optional<PluCode> plucode() {
        return Optional.ofNullable(plucode);
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

    static class Builder {
        private final SellableFormId id;
        private FormLabel formLabel = null;
        private Integer baseUnitQuantity = 1;
        private Barcode barcode = null;
        private PluCode plucode = null;

        Builder() {
            this.id = SellableFormId.generate();
        }

        Builder(SellableFormId sellableFormId) {
            this.id = sellableFormId;
        }

        Builder formLabel(FormLabel formLabel) {
            this.formLabel = formLabel;
            return this;
        }

        Builder baseUnitQuantity(Integer baseUnitQuantity) {
            this.baseUnitQuantity = baseUnitQuantity;
            return this;
        }

        Builder barcode(Barcode barcode) {
            this.barcode = barcode;
            return this;
        }

        Builder plucode(PluCode plucode) {
            this.plucode = plucode;
            return this;
        }

        SellableForm build() {
            return new SellableForm(this);
        }
    }
}
