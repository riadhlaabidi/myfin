package tn.riadh.myfin.sale.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.shared.quantity.Quantity;

public class SaleTests {

    @Test
    public void shouldEmitSaleStartedWhenSaleStarts() {
        Sale sale = Sale.start(StoreId.generate(), TerminalId.generate(), OperatorId.generate());
        assertThat(sale.getDomainEvents()).hasSize(1);
        assertThat(sale.getDomainEvents().getFirst()).isExactlyInstanceOf(SaleStarted.class);
    }

    @Test
    public void shouldEmitSaleLineAddedWhenAddingSaleLine() {
        Sale sale = Sale.start(StoreId.generate(), TerminalId.generate(), OperatorId.generate());
        SaleLine line = SaleLine.create(sale.getId(), ProductId.generate(), Quantity.ofPieces(1));
        sale.addLine(line);
        assertThat(sale.getDomainEvents()).hasSize(2);
        assertThat(sale.getDomainEvents().getLast()).isExactlyInstanceOf(SaleLineAdded.class);
    }
}
