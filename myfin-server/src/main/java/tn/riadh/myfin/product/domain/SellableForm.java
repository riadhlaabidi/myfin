package tn.riadh.myfin.product.domain;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

import org.jmolecules.ddd.types.Entity;

/**
 * A SellableForm represents a specific way a {@link Product} can be sold,
 * defined as a quantity of the product's base unit.
 * 
 * <p>
 * Examples:
 * <ul>
 * <li>A single can of "Soda" is a SellableForm with a quantity of 1 base unit,
 * identified by its own {@link Barcode}.</li>
 * <li>A pack of 6 cans is another SellableForm with a quantity of 6 base units,
 * identified by a different {@link Barcode}</li>
 * </ul>
 *
 * <p>
 * Pricing is not part of the SellableForm, instead it is managed externally via
 * a {@link PriceRecord}, which references a SellableForm by its identity.
 *
 * <p>
 * A SellableForm of a Product may or may not have an identifying
 * {@link Barcode}. The accessor returns an {@link Optional} to enforce callers
 * to handle its absence explicitly.
 *
 * <p>
 * A SellableForm is an entity managed by the {@link Product} aggregate root.
 */
public class SellableForm implements Entity<Product, SellableFormId> {
    private final SellableFormId id;
    private final FormLabel formLabel;
    private final Integer quantity;
    private final Barcode barcode;

    private SellableForm(SellableFormId id, FormLabel formLabel, Integer quantity, Barcode barcode) {
        Objects.requireNonNull(id, "SellableFormId cannot be null");

        if (quantity != null && quantity < 1) {
            throw new IllegalArgumentException("quantity should be greater than 0");
        }

        this.id = id;
        this.formLabel = formLabel;
        this.quantity = quantity;
        this.barcode = barcode;
    }

    public static SellableForm create(FormLabel formLabel, Integer conversionFactor, Barcode barcode) {
        return new SellableForm(SellableFormId.generate(), formLabel, conversionFactor, barcode);
    }

    @Override
    public SellableFormId getId() {
        return id;
    }

    public FormLabel formLabel() {
        return formLabel;
    }

    public OptionalInt quantity() {
        if (quantity == null) {
            return OptionalInt.empty();
        }
        return OptionalInt.of(quantity);
    }

    public Optional<Barcode> barcode() {
        if (barcode == null) {
            return Optional.empty();
        }
        return Optional.of(barcode);
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
