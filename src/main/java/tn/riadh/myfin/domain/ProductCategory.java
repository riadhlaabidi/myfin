package tn.riadh.myfin.domain;

public class ProductCategory extends AbstractEntity {

    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductCategory{id=" + getId() +
                ", name=" + getName() +
                "}";
    }
}
