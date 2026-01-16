package tn.riadh.myfin.infrastructure.security;

import java.util.Optional;

import org.springframework.stereotype.Component;

import tn.riadh.myfin.domain.store.Store;
import tn.riadh.myfin.domain.store.repository.StoreRepository;

@Component
public class StoreResolver {

    private class StoreResolverException extends RuntimeException {
        private StoreResolverException(String message) {
            super(message);
        }
    }

    private final StoreRepository storeRepository;

    public StoreResolver(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public Store resolveById(Long id) {
        Optional<Store> store = storeRepository.findById(id);
        if (store.isPresent()) {
            return store.get();
        }
        throw new StoreResolverException("Could not resolve store");
    }
}
