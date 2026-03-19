package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class SellableFormAlreadyExistException extends DomainException {

    public SellableFormAlreadyExistException(String message) {
        super("SELLABLE_FORM_ALREADY_EXISTS", message);
    }

}
