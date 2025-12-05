package tn.riadh.myfin.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.ProductCategory;

/**
 * Maps rows of a SQL {@link ResultSet} to {@link ProductCategory} instances.
 * <p>
 * Used by JDBC-based repositories to convert database query results
 * into fully populated {@link ProductCategory} objects.
 * </p>
 */
public class ProductCategoryMapper implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(rs.getLong("id"));
        productCategory.setName(rs.getString("name"));
        return productCategory;
    }
}
