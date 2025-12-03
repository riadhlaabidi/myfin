package tn.riadh.myfin.domain;

public class Product extends AbstractEntity {

    private String name;
    private String imageUrl;
    private ProductCategory category;

    public Product() {
    }

    public Product(String name, String imageUrl, ProductCategory category) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Product{id=" + getId() +
                ", name=" + getName() +
                ", imageUrl=" + imageUrl +
                ", category" + category.getName() +
                "}";

    }
}
