
package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import tn.riadh.myfin.product.domain.ProductId;
import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleLine;
import tn.riadh.myfin.sale.domain.SaleLineId;
import tn.riadh.myfin.sale.domain.SaleStatus;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;
import tn.riadh.myfin.shared.quantity.Quantity;
import tn.riadh.myfin.shared.quantity.UnitType;

class SaleResultSetExtractor implements ResultSetExtractor<Sale> {

    @Override
    public Sale extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<SaleLine> lines = new ArrayList<>();
        Sale saleSnapshot = null;

        while (rs.next()) {
            if (saleSnapshot == null) {
                SaleId saleId = SaleId.from(rs.getString("sale_id"));
                SaleStatus status = SaleStatus.valueOf(rs.getString("status"));
                StoreId storeId = StoreId.from(rs.getString("store_id"));
                TerminalId terminalId = TerminalId.from(rs.getString("terminal_id"));
                OperatorId operatorId = OperatorId.from(rs.getString("operator_id"));
                Instant startedAt = rs.getTimestamp("started_at").toInstant();
                Timestamp f = rs.getTimestamp("finished_at");
                Instant finishedAt = f == null ? null : f.toInstant();
                saleSnapshot = Sale.reconstitute(saleId, status, storeId, terminalId,
                        operatorId, lines, startedAt, finishedAt);
            }

            String saleLineId = rs.getString("sale_line_id");
            if (saleLineId != null) {
                SaleLineId id = SaleLineId.from(rs.getString("sale_line_id"));
                ProductId productId = ProductId.from(rs.getString("product_id"));
                Quantity quantity = Quantity.of(rs.getBigDecimal("quantity"), UnitType.valueOf(rs.getString("unit")));
                SaleLine line = SaleLine.reconstitute(id, saleSnapshot.getId(), productId, quantity);
                lines.add(line);
            }
        }

        if (saleSnapshot == null) {
            return null;
        }

        return Sale.reconstitute(
                saleSnapshot.getId(),
                saleSnapshot.status(),
                saleSnapshot.storeId(),
                saleSnapshot.terminalId(),
                saleSnapshot.operatorId(),
                lines,
                saleSnapshot.startedAt(),
                saleSnapshot.finishedAt());
    }
}
