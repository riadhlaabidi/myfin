package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.UUID;

import tn.riadh.myfin.shared.domain.AbstractDomainEvent;

class SaleLineAdded extends AbstractDomainEvent {
    private final SaleId saleId;

    private SaleLineAdded(UUID eventId, SaleId saleId, Instant occurredAt) {
        super(eventId, occurredAt);
        this.saleId = saleId;
    }

    static SaleLineAdded create(SaleId saleId) {
        return new SaleLineAdded(UUID.randomUUID(), saleId, Instant.now());
    }

    public SaleId saleId() {
        return saleId;
    }
}
