package tn.riadh.myfin.support;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcTestData {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTestData(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long productCategory() {
        String sql = "INSERT INTO product_categories (name) VALUES ('category') RETURNING id";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }

    public Long product(Long categoryId) {
        String barcode = UUID.randomUUID().toString();
        String sql = """
                INSERT INTO products (name, barcode, image_url, category_id)
                VALUES ('product', ?, 'image url', ?) RETURNING id""";
        return jdbcTemplate.queryForObject(sql, Long.class, barcode, categoryId);
    }

    public Long supplier() {
        String sql = """
                INSERT INTO suppliers (name, address, phone_number, tin)
                VALUES ('ACME', 'Address', '99999999', '99999999') RETURNING id""";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
