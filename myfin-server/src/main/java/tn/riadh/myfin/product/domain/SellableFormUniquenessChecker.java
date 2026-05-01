package tn.riadh.myfin.product.domain;

interface SellableFormUniquenessChecker {

    boolean isBarcodeUnique(Barcode barcode);

    boolean isPluCodeUnique(PluCode pluCode);
}
