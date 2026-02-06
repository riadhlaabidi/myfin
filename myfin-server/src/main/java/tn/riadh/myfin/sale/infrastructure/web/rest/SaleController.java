package tn.riadh.myfin.sale.infrastructure.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.riadh.myfin.sale.application.StartSaleService;
import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final StartSaleService startSaleService;

    public SaleController(StartSaleService startSaleService) {
        this.startSaleService = startSaleService;
    }

    @PostMapping("/start")
    public ResponseEntity<SaleId> start(@RequestBody StartSaleCommand command) {
        SaleId saleId = startSaleService.startSale(
                StoreId.from(command.getStoreId()),
                TerminalId.from(command.getTerminalId()),
                OperatorId.from(command.getOperatorId()));

        return ResponseEntity.ok(saleId);
    }
}
