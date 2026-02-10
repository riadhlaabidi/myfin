package tn.riadh.myfin.sale.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleLine;
import tn.riadh.myfin.sale.domain.SaleNotFoundException;
import tn.riadh.myfin.sale.domain.SaleRepository;
import tn.riadh.myfin.shared.quantity.Quantity;

@Service
public class AddSaleLineService {
    private final Logger logger = LoggerFactory.getLogger(AddSaleLineService.class);
    private final SaleRepository saleRepository;

    public AddSaleLineService(final SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @Transactional
    public Sale addSaleLine(final SaleId saleId, final ProductId productId, final Quantity quantity) {
        Sale sale = saleRepository
                .findById(saleId)
                .orElseThrow(() -> SaleNotFoundException.byId(saleId));
        SaleLine line = SaleLine.create(saleId, productId, quantity);
        sale.addLine(line);

        saleRepository.save(sale);
        logger.info("Added line with id {} to sale with id {}", line.getId().toString(), saleId.toString());
        return sale;
    }
}
