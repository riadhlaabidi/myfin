package tn.riadh.myfin.product.domain;

enum ProductStatus {
    ACTIVE("Active"),
    DISCONTINUED("Discontinued"),
    OUT_OF_STOCK("Out of Stock");

    private final String displayName;

    private ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    public boolean canBeSold() {
        return this == ACTIVE;
    }
}
