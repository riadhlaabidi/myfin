package tn.riadh.myfin.infrastructure.persistence.jdbc.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.product.Product;
import tn.riadh.myfin.domain.product.ProductCategory;

/**
 * Maps rows of a SQL {@link ResultSet} to {@link Product} instances.
 * <p>
 * Used by JDBC-based repositories to convert database query results
 * into fully populated {@link Product} objects. The associated
 * {@link ProductCategory} is partially populated with its identifier only.
 * </p>
 */
public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product()
                .id(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withBarcode(rs.getString("barcode"))
                .withImageUrl(rs.getString("image_url"))
                .withCategory(new ProductCategory().id(rs.getLong("category_id")));
    }
}
