package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.UUID;

import tn.riadh.myfin.shared.domain.AbstractDomainEvent;

class SaleCompleted extends AbstractDomainEvent {
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;

    private SaleCompleted(UUID eventId, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, Instant occurredAt) {
        super(eventId, occurredAt);
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
    }

    public static SaleCompleted create(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        return new SaleCompleted(UUID.randomUUID(), storeId, terminalId, operatorId, Instant.now());
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

    @Override
    public String toString() {
        return "SaleCompleted{eventId=" + eventId()
                + ", storeId=" + storeId
                + ", terminalId=" + terminalId
                + ", operatorId=" + operatorId
                + ", occurredAt=" + occurredAt();
    }
}
