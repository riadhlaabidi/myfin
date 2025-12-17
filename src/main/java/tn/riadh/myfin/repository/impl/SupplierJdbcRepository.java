package tn.riadh.myfin.repository.impl;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.Supplier;
import tn.riadh.myfin.repository.SupplierRepository;
import tn.riadh.myfin.repository.mapper.SupplierMapper;

@Repository
public class SupplierJdbcRepository implements SupplierRepository {

    private JdbcTemplate jdbcTemplate;
    private SupplierMapper supplierMapper = new SupplierMapper();

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

    @Override
    public long count() {
        String sql = "SELECT COUNT(*) FROM suppliers";
        Long countResult = jdbcTemplate.queryForObject(sql, Long.class);
        return countResult != null ? countResult : 0;
    }
}
