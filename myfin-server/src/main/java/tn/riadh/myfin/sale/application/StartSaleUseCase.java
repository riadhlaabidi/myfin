package tn.riadh.myfin.sale.application;

import org.springframework.stereotype.Service;

import tn.riadh.myfin.sale.infrastructure.persistence.jdbc.SaleJdbcRepository;

@Service
public class StartSaleUseCase {

    private final SaleJdbcRepository sales;

    public StartSaleUseCase(SaleJdbcRepository sales) {
        this.sales = sales;
    }

    public void startSale() {

    }
}
