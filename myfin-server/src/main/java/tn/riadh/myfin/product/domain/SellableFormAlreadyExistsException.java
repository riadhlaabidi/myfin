package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class SellableFormAlreadyExistsException extends DomainException {

    public SellableFormAlreadyExistsException(String message) {
        super("SELLABLE_FORM_ALREADY_EXISTS", message);
    }

    public static SellableFormAlreadyExistsException byBarcode(Barcode barcode) {
        return new SellableFormAlreadyExistsException(
                "Sellable form with barcode '" + barcode.toString() + "' already exists");
    }
}
