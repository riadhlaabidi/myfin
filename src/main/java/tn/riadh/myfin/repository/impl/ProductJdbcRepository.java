package tn.riadh.myfin.repository.impl;

import java.sql.PreparedStatement;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import tn.riadh.myfin.domain.Product;
import tn.riadh.myfin.repository.ProductRepository;
import tn.riadh.myfin.repository.mapper.ProductMapper;

/**
 * A JDBC-based implementation of {@link ProductRepository}.
 *
 * <p>
 * Uses Spring's {@link JdbcTemplate} to persist and retrieve {@link Product}
 * entities from a relational database. SQL statements are executed directly and
 * results are mapped using {@link ProductMapper}.
 * </p>
 */
public class ProductJdbcRepository implements ProductRepository {

    private JdbcTemplate jdbcTemplate;
    private ProductMapper productMapper = new ProductMapper();

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = (connection) -> {
            String sql = "INSERT INTO products(name, image_url, category) VALUES(?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getImageUrl());
            ps.setLong(3, product.getCategory().getId());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE id=?";
        Product product = jdbcTemplate.queryForObject(sql, productMapper);

        if (product != null) {
            return Optional.of(product);
        }

        return Optional.empty();
    }
}
