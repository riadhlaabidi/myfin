package tn.riadh.myfin.product.domain;

import java.util.HashSet;
import java.util.Set;

class InMemorySellableFormUniquenessChecker implements SellableFormUniquenessChecker {

    private final Set<Barcode> barcodes = new HashSet<>();
    private final Set<PluCode> plucodes = new HashSet<>();

    void withUsedBarcode(Barcode barcode) {
        barcodes.add(barcode);
    }

    void withUsedPluCode(PluCode pluCode) {
        plucodes.add(pluCode);
    }

    @Override
    public boolean isBarcodeUnique(Barcode barcode) {
        return !barcodes.contains(barcode);
    }

    @Override
    public boolean isPluCodeUnique(PluCode pluCode) {
        return !plucodes.contains(pluCode);
    }
}
