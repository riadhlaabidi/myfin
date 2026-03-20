package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class BarcodeAlreadyExistsException extends DomainException {

    public BarcodeAlreadyExistsException(Barcode barcode) {
        super("BARCODE_ALREADY_EXISTS", "Barcode '" + barcode.toString() + "' already exists");
    }

}
