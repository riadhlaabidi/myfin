package tn.riadh.myfin.infrastructure.persistence.jdbc.product;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.product.ProductCategory;

/**
 * Maps rows of a SQL {@link ResultSet} to {@link ProductCategory} instances.
 * <p>
 * Used by JDBC-based repositories to convert database query results
 * into fully populated {@link ProductCategory} objects.
 * </p>
 */
public class ProductCategoryRowMapper implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProductCategory()
                .id(rs.getLong("id"))
                .withName(rs.getString("name"));
    }
}
