package tn.riadh.myfin.sale.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleNotFoundException;
import tn.riadh.myfin.sale.domain.SaleRepository;

@Service
public class FinalizeSaleService {
    private final Logger logger = LoggerFactory.getLogger(FinalizeSaleService.class);
    private final SaleRepository saleRepository;

    public FinalizeSaleService(final SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public void finalizeSale(final SaleId saleId) {
        Sale sale = saleRepository
                .findById(saleId)
                .orElseThrow(() -> SaleNotFoundException.byId(saleId));
        sale.complete();
        logger.info("Finalized sale with id {}", saleId);
    }
}
