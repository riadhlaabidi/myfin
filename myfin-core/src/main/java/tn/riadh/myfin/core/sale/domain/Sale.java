package tn.riadh.myfin.core.sale.domain;

import tn.riadh.myfin.core.operator.domain.OperatorId;
import tn.riadh.myfin.core.store.domain.StoreId;
import tn.riadh.myfin.core.terminal.domain.TerminalId;

/**
 * Aggregate root representing a retail sale.
 * <p>
 * Encapsulates the data associated with a transaction recorded at a point of
 * sale.
 * <p>
 * Instances of this class are identified by a SaleId and are treated
 * as immutable domain objects once created.
 */
public final class Sale {
    private final SaleId id;
    private final StoreId storeId;
    private final TerminalId terminalId;
    private final OperatorId operatorId;
    private SaleStatus status;

    private Sale(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        this.id = SaleId.generate();
        this.status = SaleStatus.OPEN;
        this.storeId = storeId;
        this.terminalId = terminalId;
        this.operatorId = operatorId;
    }

    public static Sale create(StoreId storeId, TerminalId terminalId, OperatorId operatorId) {
        return new Sale(storeId, terminalId, operatorId);
    }

    public SaleId id() {
        return id;
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

    public SaleStatus status() {
        return status;
    }
}
