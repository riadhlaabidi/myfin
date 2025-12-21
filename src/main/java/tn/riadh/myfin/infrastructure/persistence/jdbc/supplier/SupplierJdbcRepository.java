package tn.riadh.myfin.infrastructure.persistence.jdbc.supplier;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.supplier.Supplier;
import tn.riadh.myfin.domain.supplier.repository.SupplierRepository;

@Repository
@Profile("jdbc")
public class SupplierJdbcRepository implements SupplierRepository {

    private JdbcTemplate jdbcTemplate;
    private SupplierRowMapper supplierMapper = new SupplierRowMapper();

    public SupplierJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Supplier save(Supplier supplier) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = (connection) -> {
            String sql = "INSERT INTO suppliers(name, address, phone_number, tin) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getAddress());
            ps.setString(3, supplier.getPhoneNumber());
            ps.setString(4, supplier.getTin());
            return ps;
        };
        jdbcTemplate.update(psc, keyHolder);
        supplier.setId(keyHolder.getKey().longValue());
        return supplier;
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        String sql = "SELECT * FROM suppliers WHERE id = ?";
        List<Supplier> suppliers = jdbcTemplate.query(sql, supplierMapper, id);
        if (suppliers.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(suppliers.getFirst());
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM suppliers WHERE id = ?)";
        Boolean exists = jdbcTemplate.queryForObject(sql, Boolean.class, id);
        return exists != null ? exists : false;
    }
}
