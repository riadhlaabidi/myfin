package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;

public class SupplyResultSetExtractor implements ResultSetExtractor<Supply> {

    @Override
    public Supply extractData(ResultSet rs) throws SQLException, DataAccessException {
        Supply supply = null;
        while (rs.next()) {
            if (supply == null) {
                supply = new Supply()
                        .id(rs.getLong("id"))
                        .withSupplier(new Supplier().id(rs.getLong("supplier_id")))
                        .withInvoiceNumber(rs.getString("invoice_number"))
                        .withSupplyDate(rs.getTimestamp("supply_date").toInstant())
                        .withTotal(rs.getDouble("total"));
            }

            SupplyItem item = new SupplyItem()
                    .id(rs.getLong("supply_item_id"))
                    .withSupply(supply)
                    .withProduct(new Product().id(rs.getLong("product_id")))
                    .withUnits(rs.getInt("units"))
                    .withSubtotal(rs.getDouble("subtotal"));

            supply.addSupplyItem(item);
        }
        return supply;
    }
}
