package tn.riadh.myfin.infrastructure.persistence.jdbc.store;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import tn.riadh.myfin.domain.store.repository.StoreRepository;
import tn.riadh.myfin.domain.store.Store;

import java.util.Optional;

@Repository
@Profile("jdbc")
public class StoreJdbcRepository implements StoreRepository {

    @Override
    public Optional<Store> findById(Long storeId) {
        return Optional.empty();
    }

}
