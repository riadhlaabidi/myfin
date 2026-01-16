package tn.riadh.myfin.domain.sale.repository;

import java.util.Optional;
import java.util.UUID;

import tn.riadh.myfin.domain.sale.Sale;

public interface SaleRepository {
    Sale save(Sale sale);

    Optional<Sale> findById(UUID id);
}
