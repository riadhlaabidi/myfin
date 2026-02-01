package tn.riadh.myfin.sale.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.event.types.DomainEvent;

/**
 * Aggregate root representing a retail sale.
 */
public final class Sale implements AggregateRoot<Sale, SaleId> {
    private final SaleId id;
    private SaleStatus status;
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;
    private final List<SaleLine> lines;
    private final Instant startedAt;
    private final Instant completedAt;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Sale(SaleId id, SaleStatus status, StoreId storeId, TerminalId terminalId, OperatorId operatorId,
            List<SaleLine> lines, ) {
        if (id == null) {
            throw new IllegalArgumentException("SaleId cannot be null");
        }
        if (storeId == null) {
            throw new IllegalArgumentException("StoreId cannot be null");
        }
        if (terminalId == null) {
            throw new IllegalArgumentException("TerminalId cannot be null");
        }
        if (operatorId == null) {
            throw new IllegalArgumentException("OperatorId cannot be null");
        }
        this.id = id;
        this.status = status;
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
        this.lines = lines;
    }

    public static Sale start(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        Sale sale = new Sale(SaleId.generate(), SaleStatus.OPEN, storeId, terminalId, operatorId, new ArrayList<>());
        sale.registerEvent(SaleStarted.create(sale.id, storeId, terminalId, operatorId));
        return sale;
    }

    public static Sale reconstitute(SaleId id, SaleStatus status, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, List<SaleLine> lines) {
        return new Sale(id, status, storeId, terminalId, operatorId, lines);
    }

    public void addLine(SaleLine line) {
        if (line == null) {
            throw new IllegalArgumentException("SaleLine cannot be null");
        }
        lines.add(line);
        registerEvent(SaleLineAdded.create(id));
    }

    public List<SaleLine> getLines() {
        return Collections.unmodifiableList(lines);
    }

    public void complete() {
        if (status.isCompleted() || status.isVoided()) {
            throw new IllegalStateException("Cannot complete a sale in " + status.displayName() + " state");
        }
        this.status = SaleStatus.COMPLETED;
        registerEvent(SaleCompleted.create(storeId, terminalId, operatorId));
    }

    @Override
    public SaleId getId() {
        return id;
    }

    public SaleStatus getStatus() {
        return status;
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

    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    private void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sale)) {
            return false;
        }
        Sale other = (Sale) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Sale{id=" + id
                + ", status=" + status.displayName()
                + ", storeId=" + storeId
                + ", terminalId=" + terminalId
                + ", operatorId=" + operatorId;
    }
}
