package tn.riadh.myfin.infrastructure.persistence.jdbc.supply;

import java.sql.PreparedStatement;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.supply.SupplyItem;
import tn.riadh.myfin.domain.supply.repository.SupplyItemRepository;

@Repository
@Profile("jdbc")
public class SupplyItemJdbcRepository implements SupplyItemRepository {

    private JdbcTemplate jdbcTemplate;

    public SupplyItemJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SupplyItem save(SupplyItem supplyItem) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = connection -> {
            String sql = "INSERT INTO supply_items(supply_id, product_id, units, subtotal) values(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setLong(1, supplyItem.getSupply().getId());
            ps.setLong(2, supplyItem.getProduct().getId());
            ps.setInt(3, supplyItem.getUnits());
            ps.setDouble(4, supplyItem.getSubtotal());
            return ps;
        };

        jdbcTemplate.update(psc, keyHolder);
        supplyItem.setId(keyHolder.getKey().longValue());
        return supplyItem;
    }

}
