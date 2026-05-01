package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class UnidentifiableSellableFormException extends DomainException {

    public UnidentifiableSellableFormException() {
        super("UNIDENTIFIABLE_SELLABLE_FORM", "SellableForm should either have a Barcode or a PluCode");
    }
}
