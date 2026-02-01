package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.infrastructure.config.MyfinConstants;
import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleRepository;

@Repository
@Profile(MyfinConstants.JDBC)
public class SaleJdbcRepository implements SaleRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SaleRowMapper rowMapper = new SaleRowMapper();

    public SaleJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Sale sale) {
        String sql = """
                INSERT INTO sales(id, store_id, terminal_id, operator_id)
                VALUES (:id, :store_id, :terminal_id, :operator_id)""";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", sale.getId().value());
        params.addValue("store_id", sale.getStoreId().value());
        params.addValue("terminal_id", sale.getTerminalId().value());
        params.addValue("operator_id", sale.getOperatorId().value());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public Optional<Sale> findById(SaleId id) {
        String sql = """
                    SELECT
                        id,
                        store_id,
                        terminal_id,
                        operator_id
                    FROM sales
                    WHERE id = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id.value());

        List<Sale> sale = jdbcTemplate.query(sql, rowMapper);
        if (sale.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(sale.getFirst());
    }

}
