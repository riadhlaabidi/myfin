package tn.riadh.myfin.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import tn.riadh.myfin.domain.Supplier;

public class SupplierMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getLong("id"));
        supplier.setName(rs.getString("name"));
        supplier.setAddress(rs.getString("address"));
        supplier.setPhoneNumber(rs.getString("phone_number"));
        supplier.setTin(rs.getString("tin"));
        return supplier;
    }
}
