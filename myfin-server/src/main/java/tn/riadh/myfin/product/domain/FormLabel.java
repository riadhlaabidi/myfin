package tn.riadh.myfin.product.domain;

enum FormLabel {
    PACK("Pack"),
    CARTON("Carton"),
    SAC("Sac");

    private String displayName;

    private FormLabel(String displayName) {
        this.displayName = displayName;
    }

    String displayName(int quantity) {
        if (quantity > 1) {
            return displayName + "s";
        }
        return displayName;
    }

    String displayName() {
        return displayName;
    }

}
