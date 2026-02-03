
package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.sale.domain.OperatorId;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleStatus;
import tn.riadh.myfin.sale.domain.StoreId;
import tn.riadh.myfin.sale.domain.TerminalId;

public class SaleRowMapper implements RowMapper<Sale> {

    @Override
    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Sale.reconstitute(
                SaleId.from(rs.getString("id")),
                SaleStatus.valueOf(rs.getString("status")),
                StoreId.from(rs.getString("store_id")),
                TerminalId.from(rs.getString("terminal_id")),
                OperatorId.from(rs.getString("operator_id")),
                new ArrayList<>(),
                rs.getTimestamp("started_at").toInstant(),
                rs.getTimestamp("finishedAt").toInstant());
    }
}
