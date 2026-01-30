package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.UUID;

import tn.riadh.myfin.shared.domain.AbstractDomainEvent;

class SaleStarted extends AbstractDomainEvent {
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;

    private SaleStarted(UUID eventId, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, Instant occurredAt) {
        super(eventId, occurredAt);
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
    }

    public static SaleStarted create(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        return new SaleStarted(UUID.randomUUID(), storeId, terminalId, operatorId, Instant.now());
    }

    public StoreId getStoreId() {
        return storeId;
    }

    public TerminalId getTerminalId() {
        return terminalId;
    }

    public OperatorId getOperatorId() {
        return operatorId;
    }
}
