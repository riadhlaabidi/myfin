package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.UUID;

import tn.riadh.myfin.shared.domain.AbstractDomainEvent;

final class SaleStarted extends AbstractDomainEvent {
    private final SaleId saleId;
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;

    private SaleStarted(UUID eventId, SaleId saleId, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, Instant occurredAt) {
        super(eventId, occurredAt);
        this.saleId = saleId;
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
    }

    public static SaleStarted create(SaleId saleId, StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        return new SaleStarted(UUID.randomUUID(), saleId, storeId, terminalId, operatorId, Instant.now());
    }

    public SaleId saleId() {
        return saleId;
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
}
