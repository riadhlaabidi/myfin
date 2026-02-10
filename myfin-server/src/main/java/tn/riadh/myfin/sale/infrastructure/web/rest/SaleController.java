package tn.riadh.myfin.sale.infrastructure.web.rest;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.sale.application.AddSaleLineService;
import tn.riadh.myfin.sale.application.FinalizeSaleService;
import tn.riadh.myfin.sale.application.StartSaleService;
import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;
import tn.riadh.myfin.shared.quantity.Quantity;
import tn.riadh.myfin.shared.quantity.Unit;

@RestController
@RequestMapping("/api/sales")
final class SaleController {

    private final StartSaleService startSaleService;
    private final AddSaleLineService addSaleLineService;
    private final FinalizeSaleService finalizeSaleService;

    SaleController(StartSaleService startSaleService, AddSaleLineService addSaleLineService,
            FinalizeSaleService finalizeSaleService) {
        this.startSaleService = startSaleService;
        this.addSaleLineService = addSaleLineService;
        this.finalizeSaleService = finalizeSaleService;
    }

    /**
     * Starts a sale operation.
     */
    @PostMapping("/start")
    ResponseEntity<StartSaleResponse> start(@RequestBody StartSaleCommand command) {
        Sale sale = startSaleService.startSale(
                StoreId.from(command.getStoreId()),
                TerminalId.from(command.getTerminalId()),
                OperatorId.from(command.getOperatorId()));

        StartSaleResponse response = new StartSaleResponse(sale.getId().value(), sale.status());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{saleId}/lines")
    ResponseEntity<Sale> addLine(@PathVariable final String saleId, @RequestBody final AddSaleLineCommand command) {
        SaleId id = SaleId.from(saleId);
        ProductId productId = ProductId.from(command.getProductId());
        Quantity quantity = Quantity.of(new BigDecimal(command.getQuantity()), Unit.valueOf(command.getUnit()));
        Sale sale = addSaleLineService.addSaleLine(id, productId, quantity);
        return ResponseEntity.ok(sale);
    }

    @PostMapping("/{saleId}/finalize")
    ResponseEntity<Void> finalizeSale(@PathVariable final SaleId saleId) {
        finalizeSaleService.finalizeSale(saleId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
