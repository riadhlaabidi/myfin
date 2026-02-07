package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.sale.domain.Sale;
import tn.riadh.myfin.sale.domain.SaleId;
import tn.riadh.myfin.sale.domain.SaleLine;
import tn.riadh.myfin.sale.domain.SaleRepository;

@Repository
@Profile("jdbc")
public class SaleJdbcRepository implements SaleRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private SaleRowMapper rowMapper = new SaleRowMapper();

    public SaleJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Sale sale) {
        String sql = """
                INSERT INTO sales(id, status, store_id, terminal_id, operator_id, started_at)
                VALUES (:id, :status, :store_id, :terminal_id, :operator_id, :started_at)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", sale.getId().value())
                .addValue("status", sale.status().toString())
                .addValue("store_id", sale.storeId().value())
                .addValue("terminal_id", sale.terminalId().value())
                .addValue("operator_id", sale.operatorId().value())
                .addValue("started_at", Timestamp.from(sale.startedAt()));

        jdbcTemplate.update(sql, params);

        saveSaleLines(sale.lines());
    }

    @Override
    public Optional<Sale> findById(SaleId id) {
        String sql = """
                    SELECT
                        id,
                        store_id,
                        terminal_id,
                        operator_id,
                        started_at,
                        finished_at
                    FROM sales
                    WHERE id = :id
                """;

        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id.value());

        List<Sale> sale = jdbcTemplate.query(sql, params, rowMapper);
        if (sale.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(sale.getFirst());
    }

    private void saveSaleLines(List<SaleLine> saleLines) {
        // TODO: Update lines accordingly (orphaned)
        if (saleLines.isEmpty()) {
            return;
        }
        String sql = """
                INSERT INTO sale_lines(id, sale_id, product_id, quantity, unit)
                VALUES(:id, :sale_id, :product_id, :quantity, :unit)
                """;
        int linesCount = saleLines.size();
        SqlParameterSource[] params = new MapSqlParameterSource[linesCount];

        for (int i = 0; i < linesCount; i++) {
            params[i] = new MapSqlParameterSource()
                    .addValue("id", saleLines.get(i).getId().value())
                    .addValue("sale_id", saleLines.get(i).saleId().value())
                    .addValue("product_id", saleLines.get(i).productId().value())
                    .addValue("quantity", saleLines.get(i).quantity().amount())
                    .addValue("unit", saleLines.get(i).quantity().unit().toString());
        }

        jdbcTemplate.batchUpdate(sql, params);
    }
}
