package tn.riadh.myfin.sale.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleRepository;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;

@Service
public class StartSaleService {
    private final Logger logger = LoggerFactory.getLogger(StartSaleService.class);

    private final SaleRepository saleRepository;

    public StartSaleService(final SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public SaleId startSale(final StoreId storeId, final TerminalId terminalId, final OperatorId operatorId) {
        Sale sale = Sale.start(storeId, terminalId, operatorId);

        saleRepository.save(sale);
        logger.info("Started a new sale with id {}", sale.getId().toString());

        return sale.getId();
    }
}
