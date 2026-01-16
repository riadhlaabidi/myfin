package tn.riadh.myfin.application.sale;

import tn.riadh.myfin.domain.common.MonetaryAmount;

public interface SaleService {
    Long openSale();

    void addLine(Long saleId, Long productId, int quantity, MonetaryAmount unitPrice);

    void removeLine(Long saleId, Long lineId);

    void applyPayment(Long saleId, MonetaryAmount amount);

    void completeSale(Long saleId);
}
