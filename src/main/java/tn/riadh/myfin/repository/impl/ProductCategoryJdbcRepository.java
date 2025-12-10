package tn.riadh.myfin.repository.impl;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.ProductCategory;
import tn.riadh.myfin.repository.ProductCategoryRepository;
import tn.riadh.myfin.repository.mapper.ProductCategoryMapper;

/**
 * JDBC-based implementation of {@link ProductCategoryRepository}.
 * <p>
 * Uses Spring's {@link JdbcTemplate} to persist and retrieve
 * {@link ProductCategory} entities from a relational database.
 * SQL statements are executed directly, and results are mapped
 * using {@link ProductCategoryMapper} .
 * </p>
 */
@Repository
public class ProductCategoryJdbcRepository implements ProductCategoryRepository {

    private JdbcTemplate jdbcTemplate;
    private ProductCategoryMapper productCategoryMapper = new ProductCategoryMapper();

    public ProductCategoryJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = connection -> {
            String sql = "INSERT INTO product_categories (name) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, productCategory.getName());
            return ps;
        };
        jdbcTemplate.update(psc, keyHolder);
        productCategory.setId(keyHolder.getKey().longValue());
        return productCategory;
    }

    @Override
    public Optional<ProductCategory> findById(Long id) {
        String sql = "SELECT * FROM product_categories WHERE id = ?";
        List<ProductCategory> categories = jdbcTemplate.query(sql, productCategoryMapper, id);
        if (categories.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(categories.getFirst());
    }
}
