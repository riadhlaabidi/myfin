package tn.riadh.myfin.product.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.jmolecules.ddd.types.AggregateRoot;

import tn.riadh.myfin.shared.quantity.UnitType;

public final class Product implements AggregateRoot<Product, ProductId> {

    private final ProductId id;
    private final ProductName name;
    private ProductStatus status;
    private CategoryId categoryId;
    private final UnitType baseUnit;
    private final List<SellableForm> sellableForms;

    private Product(final ProductId id,
            final ProductName name,
            final ProductStatus status,
            final CategoryId categoryId,
            final UnitType baseUnit,
            final List<SellableForm> sellableForms) {
        Objects.requireNonNull(id, "ProductId cannot be null");
        Objects.requireNonNull(name, "ProductName cannot be null");
        Objects.requireNonNull(categoryId, "CategoryId cannot be null");
        Objects.requireNonNull(baseUnit, "baseUnit cannot be null");
        Objects.requireNonNull(sellableForms, "sellableForms cannot be null");

        if (sellableForms.isEmpty()) {
            throw new ProductWithNoSellableFormsException();
        }

        for (SellableForm sf : sellableForms) {
            if (baseUnit.isWeighable() && sf.quantity().isPresent()) {
                throw IncompatibleSellableFormException.weighable();
            }
            if (baseUnit.isCountable() && sf.quantity().isEmpty()) {
                throw IncompatibleSellableFormException.countable();
            }
        }

        this.id = id;
        this.name = name;
        this.status = status;
        this.categoryId = categoryId;
        this.baseUnit = baseUnit;
        this.sellableForms = new ArrayList<>(sellableForms);
    }

    public static Product create(String name, CategoryId categoryId, UnitType baseUnit,
            List<SellableForm> sellableForms) {
        return new Product(
                ProductId.generate(),
                ProductName.of(name),
                ProductStatus.ACTIVE,
                categoryId,
                baseUnit,
                sellableForms);
    }

    public void addSellableForm(FormLabel formLabel, Integer quantity, Barcode barcode) {
        for (SellableForm sf : sellableForms) {
            if (sf.formLabel() != null
                    && sf.formLabel().equals(formLabel) && sf.quantity().isPresent()
                    && sf.quantity().getAsInt() == quantity
                    && sf.barcode().isPresent()
                    && sf.barcode().get().equals(barcode)) {
                throw new SellableFormAlreadyExistException("SellableForm already exists");
            }
        }
        SellableForm sellableForm = SellableForm.create(formLabel, quantity, barcode);
        sellableForms.add(sellableForm);
    }

    @Override
    public ProductId getId() {
        return id;
    }

    public ProductName name() {
        return name;
    }

    public ProductStatus status() {
        return status;
    }

    public CategoryId categoryId() {
        return categoryId;
    }

    public UnitType baseUnit() {
        return baseUnit;
    }

    public List<SellableForm> sellableForms() {
        return Collections.unmodifiableList(sellableForms);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Product)) {
            return false;
        }
        Product other = (Product) obj;
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
