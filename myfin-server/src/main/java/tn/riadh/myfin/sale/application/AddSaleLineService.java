package tn.riadh.myfin.sale.application;

import java.util.Optional;

import org.springframework.stereotype.Service;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleLine;
import tn.riadh.myfin.sale.domain.SaleNotFoundException;
import tn.riadh.myfin.sale.domain.SaleRepository;
import tn.riadh.myfin.shared.quantity.Quantity;

@Service
public class AddSaleLineService {

    private final SaleRepository saleRepository;

    public AddSaleLineService(final SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void addSaleLine(SaleId saleId, ProductId productId, Quantity quantity) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> SaleNotFoundException.byId(saleId));
        SaleLine line = SaleLine.create(saleId, productId, quantity);
        sale.addLine(line);
    }

}
