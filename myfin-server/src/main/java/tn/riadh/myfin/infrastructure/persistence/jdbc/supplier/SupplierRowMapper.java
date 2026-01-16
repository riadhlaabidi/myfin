package tn.riadh.myfin.infrastructure.persistence.jdbc.supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.supplier.Supplier;

public class SupplierRowMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Supplier()
                .id(rs.getLong("id"))
                .withName(rs.getString("name"))
                .withAddress(rs.getString("address"))
                .withPhoneNumber(rs.getString("phone_number"))
                .withTin(rs.getString("tin"));
    }
}
