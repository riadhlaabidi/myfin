package tn.riadh.myfin.shared.quantity;

public enum Unit {
    PIECE("Piece"),
    KG("Kg"),
    LITER("Liter");

    private String displayName;

    private Unit(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }
}
