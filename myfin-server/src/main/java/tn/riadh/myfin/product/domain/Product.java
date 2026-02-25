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
    private final ProductName name;
    private ProductStatus status;
    private final UnitType baseUnit;
    private final Set<SellableForm> sellableForms;

    private Product(final ProductId id,
            final ProductName name,
            final ProductStatus status,
            final UnitType baseUnit,
            final Set<SellableForm> sellableForms) {
        Objects.requireNonNull(id, "ProductId cannot be null");
        Objects.requireNonNull(name, "ProductName cannot be null");
        Objects.requireNonNull(baseUnit, "baseUnit cannot be null");
        Objects.requireNonNull(sellableForms, "sellableForms cannot be null");
        this.id = id;
        this.name = name;
        this.status = status;
        this.baseUnit = baseUnit;
        this.sellableForms = sellableForms;
    }

    public static Product create(String name, UnitType baseUnit) {
        return new Product(
                ProductId.generate(),
                ProductName.of(name),
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
