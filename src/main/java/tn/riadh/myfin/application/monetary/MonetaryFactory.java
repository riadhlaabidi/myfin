package tn.riadh.myfin.application.monetary;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import tn.riadh.myfin.domain.common.MonetaryAmount;
import tn.riadh.myfin.infrastructure.context.MonetaryContext;

@Component
public class MonetaryFactory {

    public MonetaryAmount amount(BigDecimal amount) {
        return new MonetaryAmount(amount, MonetaryContext.getCurrency());
    }

    public MonetaryAmount zero() {
        return new MonetaryAmount(BigDecimal.ZERO, MonetaryContext.getCurrency());
    }
}
