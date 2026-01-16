package tn.riadh.myfin.domain.store.repository;

import java.util.Optional;

import tn.riadh.myfin.domain.store.Store;

public interface StoreRepository {
    /**
     * Finds a {@link Store} by its identifier.
     * 
     * @param storeId the store identifier
     * @return an {@code Optional} containing the {@link Store} if found,
     *         {@link Optional#empty()} otherwise
     */
    Optional<Store> findById(Long storeId);

}
