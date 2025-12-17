package tn.riadh.myfin.domain;

/**
 * Represents a product entity.
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

    public Product(String name, String barcode, String imageUrl, ProductCategory category) {
        this.name = name;
        this.barcode = barcode;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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
