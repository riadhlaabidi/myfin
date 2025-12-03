package tn.riadh.myfin.repository.mapper;

import tn.riadh.myfin.domain.Product;
import tn.riadh.myfin.domain.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(rs.getLong("category"));

        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setImageUrl(rs.getString("image_url"));
        product.setCategory(productCategory);
        return product;
    }

}
