package tn.riadh.myfin.product.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.jmolecules.ddd.types.AggregateRoot;

import tn.riadh.myfin.shared.quantity.UnitOfMesure;

public final class Product implements AggregateRoot<Product, ProductId> {

    private final ProductId id;
    private final ProductName name;
    private ProductStatus status;
    private CategoryId categoryId;
    private final UnitOfMesure baseUnit;
    private final List<SellableForm> sellableForms;

    private Product(final ProductId id,
            final ProductName name,
            final ProductStatus status,
            final CategoryId categoryId,
            final UnitOfMesure baseUnit) {
        Objects.requireNonNull(id, "ProductId cannot be null");
        Objects.requireNonNull(name, "ProductName cannot be null");
        Objects.requireNonNull(categoryId, "CategoryId cannot be null");
        Objects.requireNonNull(baseUnit, "baseUnit cannot be null");

        // if (sellableForms.isEmpty()) {
        // throw new ProductWithNoSellableFormsException();
        // }

        this.id = id;
        this.name = name;
        this.status = status;
        this.categoryId = categoryId;
        this.baseUnit = baseUnit;
        this.sellableForms = new ArrayList<>();
    }

    /**
     * thoughts:
     * Creating a product should or should not add sellable forms?
     * - Create with base unit's barcode and other properties and then add others!
     * - Domain service for creating!
     * what to do here?
     */
    static Product create(String name, CategoryId categoryId, UnitOfMesure baseUnit,
            FormLabel formLabel, Barcode barcode, PluCode pluCode) {
        Product newProduct = new Product(
                ProductId.generate(),
                ProductName.of(name),
                ProductStatus.ACTIVE,
                categoryId,
                baseUnit);

        // add base unit sellable form
        newProduct.addSellableForm(formLabel, 1, barcode, pluCode);

        return newProduct;
    }

    void addSellableForm(FormLabel formLabel, Integer quantity, Barcode barcode, PluCode pluCode) {
        if (baseUnit.isWeighable()) {
            if (!sellableForms().isEmpty()) {
                throw IncompatibleSellableFormException.weighable();
            }
        }
        // TODO: this is slop, need to review the legality of checking duplicates in
        // here.
        for (SellableForm sf : sellableForms) {
            if (sf.formLabel() != null
                    && sf.formLabel().equals(formLabel)
                    && sf.baseUnitQuantity() == quantity
                    && sf.barcode().isPresent()
                    && sf.barcode().get().equals(barcode)) {
                throw new SellableFormAlreadyExistsException("SellableForm already exists");
            }
        }
        SellableForm sf = new SellableForm.Builder()
                .formLabel(formLabel)
                .baseUnitQuantity(quantity)
                .barcode(barcode)
                .build();
        sellableForms.add(sf);
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

    public UnitOfMesure baseUnit() {
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
