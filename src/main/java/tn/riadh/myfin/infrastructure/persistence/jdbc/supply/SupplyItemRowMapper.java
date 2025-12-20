package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;

public class SupplyItemRowMapper implements RowMapper<SupplyItem> {

    @Override
    public SupplyItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SupplyItem()
                .id(rs.getLong("id"))
                .withSupply(new Supply().id(rs.getLong("supply_id")))
                .withProduct(new Product().id(rs.getLong("product_id")))
                .withUnits(rs.getInt("units"))
                .withSubtotal(rs.getDouble("subtotal"));
    }
}
