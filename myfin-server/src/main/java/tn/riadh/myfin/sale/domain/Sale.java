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
    private Instant finishedAt;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Sale(SaleId id, SaleStatus status, StoreId storeId, TerminalId terminalId, OperatorId operatorId,
            List<SaleLine> lines, Instant startedAt, Instant finishedAt) {
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
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
    }

    public static Sale start(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        Sale sale = new Sale(SaleId.generate(), SaleStatus.OPEN, storeId, terminalId, operatorId, new ArrayList<>(),
                Instant.now(), null);
        sale.registerEvent(SaleStarted.create(sale.id, storeId, terminalId, operatorId));
        return sale;
    }

    public static Sale reconstitute(SaleId id, SaleStatus status, StoreId storeId, TerminalId terminalId,
            OperatorId operatorId, List<SaleLine> lines, Instant startedAt, Instant finishedAt) {
        return new Sale(id, status, storeId, terminalId, operatorId, lines, startedAt, finishedAt);
    }

    public void addLine(SaleLine line) {
        if (line == null) {
            throw new IllegalArgumentException("SaleLine cannot be null");
        }
        lines.add(line);
        registerEvent(SaleLineAdded.create(id));
    }

    public void complete() {
        ensureModifiable();

        if (lines.isEmpty()) {
            throw new IllegalStateException("Cannot complete a sale with 0 lines");
        }

        this.status = SaleStatus.COMPLETED;
        this.finishedAt = Instant.now();
        registerEvent(SaleCompleted.create(storeId, terminalId, operatorId));
    }

    public void voidSale() {
        ensureModifiable();

        this.status = SaleStatus.VOIDED;
        this.finishedAt = Instant.now();
        registerEvent(SaleVoided.create(id));
    }

    @Override
    public SaleId getId() {
        return id;
    }

    public SaleStatus status() {
        return status;
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

    public List<SaleLine> lines() {
        return Collections.unmodifiableList(lines);
    }

    public Instant startedAt() {
        return startedAt;
    }

    public Instant finishedAt() {
        return finishedAt;
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
                + ", operatorId=" + operatorId
                + ", startedAt=" + startedAt
                + ", finishedAt=" + finishedAt;
    }

    private void ensureModifiable() {
        if (!status.isOpen()) {
            throw new IllegalStateException("Cannot modify sale in " + status.displayName() + " state");
        }
    }
}
