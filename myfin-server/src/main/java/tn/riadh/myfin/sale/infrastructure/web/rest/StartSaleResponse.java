package tn.riadh.myfin.sale.infrastructure.web.rest;

import java.io.Serializable;
import java.util.UUID;

import tn.riadh.myfin.sale.domain.SaleStatus;

final class StartSaleResponse implements Serializable {
    private UUID saleId;
    private SaleStatus status;

    public StartSaleResponse(UUID saleId, SaleStatus status) {
        this.saleId = saleId;
        this.status = status;
    }

    public UUID getSaleId() {
        return saleId;
    }

    public void setSaleId(UUID saleId) {
        this.saleId = saleId;
    }

    public SaleStatus getStatus() {
        return status;
    }

    public void setStatus(SaleStatus status) {
        this.status = status;
    }
}
