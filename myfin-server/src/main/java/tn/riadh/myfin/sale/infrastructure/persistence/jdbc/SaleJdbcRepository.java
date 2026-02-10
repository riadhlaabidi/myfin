package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
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
    private SaleResultSetExtractor saleRse = new SaleResultSetExtractor();

    public SaleJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Sale sale) {
        if (existsById(sale.getId())) {
            update(sale);
        } else {
            insert(sale);
        }
        saveSaleLines(sale.getId(), sale.lines());
    }

    @Override
    public Optional<Sale> findById(SaleId id) {
        String sql = """
                    SELECT
                        s.id AS sale_id,
                        s.status,
                        s.store_id,
                        s.terminal_id,
                        s.operator_id,
                        s.started_at,
                        s.finished_at,
                        l.id AS sale_line_id,
                        l.product_id,
                        l.quantity,
                        l.unit
                    FROM sales s
                    LEFT JOIN sale_lines l
                    ON s.id = l.sale_id
                    WHERE s.id = :id
                """;

        Sale sale = jdbcTemplate.query(sql, Map.of("id", id.value()), saleRse);
        return Optional.ofNullable(sale);
    }

    private boolean existsById(SaleId saleId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM sales WHERE id = :id)";
        Boolean exists = jdbcTemplate.queryForObject(sql, Map.of("id", saleId.value()), Boolean.class);
        return Boolean.TRUE.equals(exists);
    }

    private void insert(Sale sale) {
        String sql = """
                INSERT INTO sales(id, status, store_id, terminal_id, operator_id, started_at, finished_at)
                VALUES (:id, :status, :store_id, :terminal_id, :operator_id, :started_at, :finished_at)
                """;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", sale.getId().value())
                .addValue("status", sale.status().toString())
                .addValue("store_id", sale.storeId().value())
                .addValue("terminal_id", sale.terminalId().value())
                .addValue("operator_id", sale.operatorId().value())
                .addValue("started_at", Timestamp.from(sale.startedAt()))
                .addValue("finished_at", sale.finishedAt() == null ? null : Timestamp.from(sale.finishedAt()));

        jdbcTemplate.update(sql, params);
    }

    private void update(Sale sale) {
        String sql = """
                UPDATE sales SET
                    status = :status,
                    finished_at = :finished_at
                WHERE id = :id
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", sale.getId().value())
                .addValue("status", sale.status().toString())
                .addValue("finished_at", sale.finishedAt() == null ? null : Timestamp.from(sale.finishedAt()));

        jdbcTemplate.update(sql, params);
    }

    private void saveSaleLines(SaleId saleId, List<SaleLine> lines) {
        deleteOldSaleLines(saleId);

        if (lines.isEmpty()) {
            return;
        }

        String sql = """
                INSERT INTO sale_lines(id, sale_id, product_id, quantity, unit)
                VALUES(:id, :sale_id, :product_id, :quantity, :unit)
                """;
        int linesCount = lines.size();
        SqlParameterSource[] params = new MapSqlParameterSource[linesCount];

        for (int i = 0; i < linesCount; i++) {
            params[i] = new MapSqlParameterSource()
                    .addValue("id", lines.get(i).getId().value())
                    .addValue("sale_id", saleId.value())
                    .addValue("product_id", lines.get(i).productId().value())
                    .addValue("quantity", lines.get(i).quantity().amount())
                    .addValue("unit", lines.get(i).quantity().unit().toString());
        }

        jdbcTemplate.batchUpdate(sql, params);
    }

    private void deleteOldSaleLines(SaleId saleId) {
        String sql = "DELETE FROM sale_lines WHERE sale_id = :saleId";
        jdbcTemplate.update(sql, Map.of("saleId", saleId.value()));
    }
}
