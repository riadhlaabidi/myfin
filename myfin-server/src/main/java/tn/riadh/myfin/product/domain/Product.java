package tn.riadh.myfin.product.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.jmolecules.ddd.types.AggregateRoot;

import tn.riadh.myfin.shared.quantity.UnitType;

public final class Product implements AggregateRoot<Product, ProductId> {
    private final ProductId id;
    private ProductStatus status;
    private final UnitType baseUnit;
    private Set<SellableForm> sellableForms;

    private Product(ProductId id, Barcode barcode, ProductStatus status, UnitType baseUnit,
            Set<SellableForm> sellableForms) {
        Objects.requireNonNull(id, "ProductId cannot be null");
        Objects.requireNonNull(barcode, "Barcode cannot be null");
        Objects.requireNonNull(baseUnit, "baseUnit cannot be null");
        this.id = id;
        this.status = status;
        this.baseUnit = baseUnit;
        this.sellableForms = sellableForms;
    }

    public static Product create(String barcode, UnitType baseUnit) {
        return new Product(
                ProductId.generate(),
                Barcode.from(barcode),
                ProductStatus.ACTIVE,
                baseUnit,
                new HashSet<>());
    }

    public void addSellableForm(String name, BigDecimal conversionFactor, Optional<Barcode> barcode) {
        boolean existsByName = sellableForms.stream().anyMatch(sf -> sf.name().equalsIgnoreCase(name));
        if (existsByName) {
            throw new IllegalArgumentException(
                    "A sellable form with name '" + name + "' already exists for this product");
        }

        SellableForm sellableForm = SellableForm.create(id, name, conversionFactor, barcode);
        sellableForms.add(sellableForm);
    }

    public void removeSellableForm(SellableFormId id) {
    }

    @Override
    public ProductId getId() {
        return id;
    }

    public ProductStatus status() {
        return status;
    }

    public UnitType unit() {
        return baseUnit;
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
