package tn.riadh.myfin.sale.infrastructure.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.sale.application.AddSaleLineService;
import tn.riadh.myfin.sale.application.StartSaleService;
import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;
import tn.riadh.myfin.shared.quantity.Quantity;

@RestController
@RequestMapping("/api/sales")
final class SaleController {

    private final StartSaleService startSaleService;
    private final AddSaleLineService addSaleLineService;

    public SaleController(StartSaleService startSaleService, AddSaleLineService addSaleLineService) {
        this.startSaleService = startSaleService;
        this.addSaleLineService = addSaleLineService;
    }

    /**
     * Starts a sale operation.
     */
    @PostMapping("/start")
    public ResponseEntity<StartSaleResponse> start(@RequestBody StartSaleCommand command) {
        Sale sale = startSaleService.startSale(
                StoreId.from(command.getStoreId()),
                TerminalId.from(command.getTerminalId()),
                OperatorId.from(command.getOperatorId()));

        StartSaleResponse response = new StartSaleResponse(sale.getId().value(), sale.status());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/{saleId}/add")
    public ResponseEntity<Sale> addLine(@PathVariable String saleId, @RequestBody AddSaleLineCommand command) {
        Sale sale = addSaleLineService.addSaleLine(
                SaleId.from(saleId),
                ProductId.from(command.getProductId()),
                Quantity.ofPieces(1));
        return ResponseEntity.ok(sale);
    }
}
