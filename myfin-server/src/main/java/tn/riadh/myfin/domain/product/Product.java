package tn.riadh.myfin.domain.product;

import tn.riadh.myfin.domain.common.AbstractEntity;

/**
 * Represents a product.
 * <p>
 * Identity and equality behavior are inherited from {@link AbstractEntity}.
 * </p>
 */
public class Product extends AbstractEntity {

    private String name;
    private String barcode;
    private String imageUrl;
    private ProductCategory category;

    /**
     * Creates an empty {@code Product} instance.
     * <p>
     * Required for frameworks that rely on a no-argument constructor.
     * </p>
     */
    public Product() {
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product withName(String name) {
        this.setName(name);
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Product withBarcode(String barcode) {
        this.setBarcode(barcode);
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Product withImageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Product withCategory(ProductCategory category) {
        this.setCategory(category);
        return this;
    }

    @Override
    public String toString() {
        return "Product{id=" + getId()
                + ", name=" + name
                + ", barcode=" + barcode
                + ", imageUrl=" + imageUrl
                + ", category=" + category.getName()
                + "}";
    }
}
