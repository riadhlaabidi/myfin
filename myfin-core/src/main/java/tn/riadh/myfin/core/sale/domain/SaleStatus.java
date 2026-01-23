package tn.riadh.myfin.core.sale.domain;

enum SaleStatus {
    OPEN("Open"),
    COMPLETED("Completed"),
    VOIDED("Voided");

    private String displayName;

    private SaleStatus(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() {
        return displayName;
    }

    public boolean isOpen() {
        return this == OPEN;
    }

    public boolean isCompleted() {
        return this == COMPLETED;
    }

    public boolean isVoided() {
        return this == VOIDED;
    }
}
