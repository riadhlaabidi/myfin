package tn.riadh.myfin.infrastructure.persistence.jdbc.inventory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.inventory.InventoryItem;
import tn.riadh.myfin.domain.inventory.repository.InventoryItemRepository;

@Repository
@Profile("jdbc")
public class InventoryItemJdbcRepository implements InventoryItemRepository {

    private JdbcTemplate jdbcTemplate;
    private InventoryItemRowMapper inventoryItemRowMapper = new InventoryItemRowMapper();

    public InventoryItemJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InventoryItem save(InventoryItem inventoryItem) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = connection -> {
            String sql = "INSERT INTO inventory_items (product_id, units) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setLong(1, inventoryItem.getProduct().getId());
            ps.setInt(2, inventoryItem.getUnits());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        inventoryItem.setId(keyHolder.getKey().longValue());
        return inventoryItem;
    }

    @Override
    public Optional<InventoryItem> findById(Long id) {
        String sql = "SELECT * FROM inventory_items WHERE id = ?";
        List<InventoryItem> items = jdbcTemplate.query(sql, inventoryItemRowMapper, id);
        if (items.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(items.getFirst());
    }

    @Override
    public Optional<InventoryItem> findByProductId(Long productId) {
        String sql = "SELECT * FROM inventory_items WHERE product_id = ?";
        List<InventoryItem> items = jdbcTemplate.query(sql, inventoryItemRowMapper, productId);
        if (items.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(items.getFirst());
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM inventory_items WHERE id = ?)";
        Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return exists != null ? exists : false;
    }

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM inventory_items";
        Long countResult = jdbcTemplate.queryForObject(sql, Long.class);
        return countResult != null ? countResult : 0;
    }
}
