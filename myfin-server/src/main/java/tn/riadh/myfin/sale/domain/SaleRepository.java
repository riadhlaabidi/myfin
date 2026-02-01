package tn.riadh.myfin.sale.domain;

import java.util.Optional;

import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface SaleRepository {

    void save(Sale sale);

    Optional<Sale> findById(SaleId id);
}
