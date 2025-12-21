package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.supply.Supply;
import tn.riadh.myfin.domain.supply.SupplyItem;
import tn.riadh.myfin.domain.supply.repository.SupplyRepository;

@Repository
@Profile("jdbc")
public class SupplyJdbcRepository implements SupplyRepository {

    private JdbcTemplate jdbcTemplate;
    private SupplyRowMapper supplyMapper = new SupplyRowMapper();

    public SupplyJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Supply save(Supply supply) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = (connection) -> {
            String sql = "INSERT INTO supplies(supplier_id, invoice_number, supply_date, total) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setLong(1, supply.getSupplier().getId());
            ps.setString(2, supply.getInvoiceNumber());
            ps.setTimestamp(3, Timestamp.from(supply.getSupplyDate()));
            ps.setDouble(4, supply.getTotal());
            return ps;
        };
        jdbcTemplate.update(psc, keyHolder);
        Long supplyId = keyHolder.getKey().longValue();
        supply.setId(supplyId);
        insertSupplyItems(supplyId, supply.getSupplyItems());
        return supply;
    }

    @Override
    public Optional<Supply> findById(Long id) {
        String sql = "SELECT * FROM supplies WHERE id = ?";
        List<Supply> supplies = jdbcTemplate.query(sql, supplyMapper, id);
        if (supplies.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(supplies.getFirst());
    }

    @Override
    public Optional<Supply> findByIdWithSupplyItems(Long id) {
        String sql = """
                SELECT
                    s.id,
                    supplier_id,
                    invoice_number,
                    supply_date,
                    total,
                    si.id AS supply_item_id,
                    product_id,
                    units,
                    subtotal
                FROM supplies AS s
                LEFT JOIN supply_items AS si
                ON s.id = si.supply_id
                WHERE s.id = ?""";
        Supply supply = jdbcTemplate.query(sql, new SupplyResultSetExtractor(), id);
        if (supply == null) {
            return Optional.empty();
        }
        return Optional.of(supply);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM supplies WHERE id = ?)";
        Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return exists != null ? exists : false;
    }

    private void insertSupplyItems(Long supplyId, List<SupplyItem> items) {
        String sql = """
                INSERT INTO supply_items (supply_id, product_id, units, subtotal)
                VALUES (?, ?, ?, ?) """;

        jdbcTemplate.batchUpdate(sql, items, items.size(), (ps, item) -> {
            ps.setLong(1, supplyId);
            ps.setLong(2, item.getProduct().getId());
            ps.setInt(3, item.getUnits());
            ps.setDouble(4, item.getSubtotal());
        });
    }
}
