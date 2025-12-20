package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;

public class SupplyRowMapper implements RowMapper<Supply> {
    @Override
    public Supply mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Supply()
                .id(rs.getLong("id"))
                .withSupplier(new Supplier().id(rs.getLong("supplier_id")))
                .withInvoiceNumber(rs.getString("invoice_number"))
                .withSupplyDate(rs.getTimestamp("supply_date").toInstant())
                .withTotal(rs.getDouble("total"));
    }
}
