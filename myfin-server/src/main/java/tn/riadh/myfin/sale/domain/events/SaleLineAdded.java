package tn.riadh.myfin.sale.domain.events;

import java.time.Instant;
import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

import tn.riadh.myfin.sale.domain.SaleId;

public class SaleLineAdded implements DomainEvent {
    private final UUID eventId;
    private final SaleId saleId;
    private final Instant occurredAt;

    private SaleLineAdded(UUID eventId, SaleId saleId, Instant occurredAt) {
        this.eventId = eventId;
        this.saleId = saleId;
        this.occurredAt = occurredAt;
    }

    public static SaleLineAdded create(SaleId saleId) {
        return new SaleLineAdded(UUID.randomUUID(), saleId, Instant.now());
    }

    public UUID eventId() {
        return eventId;
    }

    public SaleId saleId() {
        return saleId;
    }

    public Instant occurredAt() {
        return occurredAt;
    }
}
