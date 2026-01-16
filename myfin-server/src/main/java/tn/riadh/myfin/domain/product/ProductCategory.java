package tn.riadh.myfin.domain.product;

import tn.riadh.myfin.domain.common.AbstractEntity;

/**
 * Represents a category used to classify products.
 * <p>
 * A product category is identified by a descriptive name and inherits
 * identity behavior from {@link AbstractEntity}. Categories are used to
 * group products logically within the application.
 * </p>
 */
public class ProductCategory extends AbstractEntity {

    private String name;

    /**
     * Creates an empty {@code ProductCategory} instance.
     * <p>
     * Required for frameworks that rely on a no-argument constructor.
     * </p>
     */
    public ProductCategory() {
    }

    public ProductCategory id(Long id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory withName(String name) {
        this.setName(name);
        return this;
    }

    @Override
    public String toString() {
        return "ProductCategory{id=" + getId() +
                ", name=" + getName() +
                "}";
    }
}
