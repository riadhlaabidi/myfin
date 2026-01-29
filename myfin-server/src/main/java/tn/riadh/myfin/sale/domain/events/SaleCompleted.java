package tn.riadh.myfin.sale.domain.events;

import java.time.Instant;
import java.util.UUID;

import org.jmolecules.event.types.DomainEvent;

import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;

public class SaleCompleted implements DomainEvent {
    private final UUID eventId;
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;
    private final Instant occurredAt;

    private SaleCompleted(UUID eventId, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, Instant occurredAt) {
        this.eventId = eventId;
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
        this.occurredAt = occurredAt;
    }

    public static SaleCompleted create(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        return new SaleCompleted(UUID.randomUUID(), storeId, terminalId, operatorId, Instant.now());
    }

    public UUID eventId() {
        return eventId;
    }

    public StoreId storeId() {
        return storeId;
    }

    public TerminalId terminalId() {
        return terminalId;
    }

    public OperatorId operatorId() {
        return operatorId;
    }

    public Instant occurredAt() {
        return occurredAt;
    }
}
