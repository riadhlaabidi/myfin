package tn.riadh.myfin.domain;

public class Inventory extends AbstractEntity {

    private Product product;
    private int units;

    public Inventory() {
    }

    public Inventory(Product product, int units) {
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
        return "Inventory{id=" + getId() +
                ", product=" + product.getId() +
                ", units" + units +
                "}";
    }
}
