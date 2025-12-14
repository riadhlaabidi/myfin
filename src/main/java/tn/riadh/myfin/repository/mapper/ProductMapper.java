package tn.riadh.myfin.repository.mapper;

import tn.riadh.myfin.domain.Product;
import tn.riadh.myfin.domain.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * Maps rows of a SQL {@link ResultSet} to {@link Product} instances.
 * <p>
 * Used by JDBC-based repositories to convert database query results
 * into fully populated {@link Product} objects. The associated
 * {@link ProductCategory} is partially populated with its identifier.
 * </p>
 */
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(rs.getLong("category_id"));

        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setBarcode(rs.getString("barcode"));
        product.setImageUrl(rs.getString("image_url"));
        product.setCategory(productCategory);
        return product;
    }

}
