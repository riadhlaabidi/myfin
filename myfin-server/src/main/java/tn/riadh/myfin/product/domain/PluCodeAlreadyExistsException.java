package tn.riadh.myfin.product.domain;

import tn.riadh.myfin.shared.domain.DomainException;

public class PluCodeAlreadyExistsException extends DomainException {

    public PluCodeAlreadyExistsException(PluCode pluCode) {
        super("PLU_CODE_ALREADY_EXISTS", "PLU code " + pluCode.toString() + " already exists");
    }
}
