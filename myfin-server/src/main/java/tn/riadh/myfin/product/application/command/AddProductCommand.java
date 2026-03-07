package tn.riadh.myfin.product.application.command;

import java.util.Set;

public class AddProductCommand {
    private String name;
    private Set<SellableFormData> sellableForms;
    private String unitType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SellableFormData> getSellableForms() {
        return sellableForms;
    }

    public void setSellableForms(Set<SellableFormData> sellableForms) {
        this.sellableForms = sellableForms;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    private class SellableFormData {
        private String formLabel;
        private String conversionFactor;
        private String barcode;

        public String getFormLabel() {
            return formLabel;
        }

        public void setFormLabel(String formLabel) {
            this.formLabel = formLabel;
        }

        public String getConversionFactor() {
            return conversionFactor;
        }

        public void setConversionFactor(String conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public String getBarcode() {
            return barcode;
        }

        public void setBarcode(String barcode) {
            this.barcode = barcode;
        }
    }
}
