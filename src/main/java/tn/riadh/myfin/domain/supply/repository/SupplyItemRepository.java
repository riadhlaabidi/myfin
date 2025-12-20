package tn.riadh.myfin.domain.supply.repository;

import tn.riadh.myfin.domain.supply.SupplyItem;

public interface SupplyItemRepository {
    /**
     * Saves the given {@link SupplyItem}.
     *
     * @param supplyItem the {@link SupplyItem} to persist
     * @return the persisted {@link SupplyItem}
     */
    SupplyItem save(SupplyItem supplyItem);
}
