package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import tn.riadh.myfin.application.monetary.MonetaryFactory;
import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;

public class SupplyResultSetExtractor implements ResultSetExtractor<Supply> {

    MonetaryFactory monetaryFactory = new MonetaryFactory();

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
                        .withTotal(monetaryFactory.amount(rs.getBigDecimal("total")));
            }

            SupplyItem item = new SupplyItem()
                    .id(rs.getLong("supply_item_id"))
                    .withProduct(new Product().id(rs.getLong("product_id")))
                    .withUnits(rs.getInt("units"))
                    .withSubtotal(monetaryFactory.amount(rs.getBigDecimal("subtotal")));

            supply.addSupplyItem(item);
        }
        return supply;
    }
}
