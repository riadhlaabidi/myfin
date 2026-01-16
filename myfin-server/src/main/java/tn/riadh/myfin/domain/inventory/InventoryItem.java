package tn.riadh.myfin.domain.inventory;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.product.Product;

/**
 * Represents an inventory item for a single product.
 * <p>
 * Each inventory item tracks a {@link Product} and the number of units
 * currently available in the inventory. Identity behavior is inherited from
 * {@link AbstractEntity}.
 * </p>
 */
public class InventoryItem extends AbstractEntity {

    private Product product;
    private int units;

    /**
     * Creates an empty {@code Inventory} instance.
     * <p>
     * Intended for frameworks that require a no-argument constructor.
     * </p>
     */
    public InventoryItem() {
    }

    public InventoryItem id(Long id) {
        this.setId(id);
        return this;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InventoryItem withProduct(Product product) {
        this.setProduct(product);
        return this;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public InventoryItem withUnits(int units) {
        this.setUnits(units);
        return this;
    }

    @Override
    public String toString() {
        return "InventoryItem{id=" + getId() +
                ", product=" + product.getId() +
                ", units" + units +
                "}";
    }
}
