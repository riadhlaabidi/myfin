package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class IncompatibleSellableFormException extends DomainException {

    public IncompatibleSellableFormException(String message) {
        super("INCOMPATIBLE_SELLABLE_FORM", message);
    }

    public static IncompatibleSellableFormException weighable() {
        return new IncompatibleSellableFormException("Cannot add a sellable form for a weighable product");
    }

    public static IncompatibleSellableFormException countable() {
        return new IncompatibleSellableFormException(
                "SellableForm's quantity cannot be null for a non-weighable product");
    }
}
