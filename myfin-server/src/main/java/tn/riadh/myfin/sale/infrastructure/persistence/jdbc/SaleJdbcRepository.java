package tn.riadh.myfin.sale.infrastructure.persistence.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
                INSERT INTO sales(id, store_id, terminal_id, operator_id, started_at, finished_at)
                VALUES (:id, :store_id, :terminal_id, :operator_id, :started_at, :finished_at)
                """;

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", sale.getId().value())
                .addValue("store_id", sale.storeId().value())
                .addValue("terminal_id", sale.terminalId().value())
                .addValue("operator_id", sale.operatorId().value())
                .addValue("started_at", sale.startedAt())
                .addValue("finished_at", sale.finishedAt());

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

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id.value());

        List<Sale> sale = jdbcTemplate.query(sql, rowMapper);
        if (sale.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(sale.getFirst());
    }

    private void saveSaleLines(List<SaleLine> saleLines) {
        String sql = """
                INSERT INTO sale_lines(id, sale_id, product_id, quantity)
                VALUES(:id, :sale_id, :product_id, :quantity)
                """;
        int linesCount = saleLines.size();
        var params = new MapSqlParameterSource[linesCount];

        for (int i = 0; i < linesCount; i++) {
            params[i] = new MapSqlParameterSource()
                    .addValue("id", saleLines.get(i).getId().value())
                    .addValue("saleId", saleLines.get(i).saleId().value())
                    .addValue("productId", saleLines.get(i).productId().value())
                    .addValue("quantity", saleLines.get(i).quantity().amount());
        }

        jdbcTemplate.batchUpdate(sql, params);
    }
}
