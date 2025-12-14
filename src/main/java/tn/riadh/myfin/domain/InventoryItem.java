package tn.riadh.myfin.domain;

/**
 * Represents an inventory item for a specific product.
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

    public InventoryItem(Product product, int units) {
        this.product = product;
        this.units = units;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "InventoryItem{id=" + getId() +
                ", product=" + product.getId() +
                ", units" + units +
                "}";
    }
}
