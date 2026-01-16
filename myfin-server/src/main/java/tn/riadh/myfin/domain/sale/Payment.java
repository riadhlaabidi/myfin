package tn.riadh.myfin.domain.sale;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.common.MonetaryAmount;

public class Payment extends AbstractEntity {
    private MonetaryAmount amount;

    public Payment() {
    }

    public Payment(MonetaryAmount amount) {
        this.amount = amount;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }
}
