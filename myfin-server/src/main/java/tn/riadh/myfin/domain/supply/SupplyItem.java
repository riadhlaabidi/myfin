package tn.riadh.myfin.domain.supply;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.common.MonetaryAmount;
import tn.riadh.myfin.domain.product.Product;

/**
 * Represents a suplpy item. A supply item belongs to a {@link Supply}, it
 * records the supplied amount of a single product in the supply event, and the
 * total price of the supplied amount of a product is represented as the
 * subtotal.
 * <p>
 * Identity and equality behavior is inherited from {@link AbstractEntity}.
 * </p>
 */
public class SupplyItem extends AbstractEntity {
    private Supply supply;
    private Product product;
    private int units;
    private MonetaryAmount subtotal;

    /**
     * Creates an empty {@code SupplyItem} instance.
     * <p>
     * Required for frameworks that rely on a no-argument constructor.
     * </p>
     */
    public SupplyItem() {
    }

    public SupplyItem id(Long id) {
        this.setId(id);
        return this;
    }

    public Supply getSupply() {
        return supply;
    }

    public void setSupply(Supply supply) {
        this.supply = supply;
    }

    public SupplyItem withSupply(Supply supply) {
        this.setSupply(supply);
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SupplyItem withProduct(Product product) {
        this.setProduct(product);
        return this;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public SupplyItem withUnits(int units) {
        this.setUnits(units);
        return this;
    }

    public MonetaryAmount getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(MonetaryAmount subtotal) {
        this.subtotal = subtotal;
    }

    public SupplyItem withSubtotal(MonetaryAmount subtotal) {
        this.setSubtotal(subtotal);
        return this;
    }

    @Override
    public String toString() {
        return "SupplyItem{id=" + getId()
                + ", supply=" + getSupply().getId()
                + ", product=" + getProduct().getId()
                + ", units=" + units
                + ", subtotal=" + subtotal
                + "}";
    }
}
