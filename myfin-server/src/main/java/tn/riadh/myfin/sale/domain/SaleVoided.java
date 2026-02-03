package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.UUID;

import tn.riadh.myfin.shared.domain.AbstractDomainEvent;

final class SaleVoided extends AbstractDomainEvent {

    private final SaleId saleId;

    private SaleVoided(UUID eventId, SaleId saleId, Instant occurredAt) {
        super(eventId, occurredAt);
        this.saleId = saleId;
    }

    public static SaleVoided create(SaleId saleId) {
        return new SaleVoided(UUID.randomUUID(), saleId, Instant.now());
    }

    public SaleId saleId() {
        return saleId;
    }
}
