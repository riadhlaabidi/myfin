package tn.riadh.myfin.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.InventoryItem;
import tn.riadh.myfin.domain.Product;

public class InventoryItemMapper implements RowMapper<InventoryItem> {

    @Override
    public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("product_id"));

        InventoryItem it = new InventoryItem();
        it.setId(rs.getLong("id"));
        it.setProduct(product);
        it.setUnits(rs.getInt("units"));
        return it;
    }
}
