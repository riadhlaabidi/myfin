package tn.riadh.myfin.infrastructure.persistence.jdbc.inventory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.inventory.InventoryItem;
import tn.riadh.myfin.domain.product.Product;

public class InventoryItemRowMapper implements RowMapper<InventoryItem> {

    @Override
    public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new InventoryItem()
                .id(rs.getLong("id"))
                .withProduct(new Product().id(rs.getLong("product_id")))
                .withUnits(rs.getInt("units"));
    }
}
