package tn.riadh.myfin.domain.supply;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tn.riadh.myfin.domain.common.AbstractEntity;
import tn.riadh.myfin.domain.common.MonetaryAmount;
import tn.riadh.myfin.domain.supplier.Supplier;

/**
 * Represents a supply event.
 * <p>
 * Identity and equality behavior is inherited from {@link AbstractEntity}.
 * </p>
 */
public class Supply extends AbstractEntity {

    private Supplier supplier;
    private String invoiceNumber;
    private Instant supplyDate;
    private List<SupplyItem> supplyItems;
    private MonetaryAmount total;

    /**
     * Creates an empty {@code Supply} instance.
     * <p>
     * Required for frameworks that rely on a no-argument constructor.
     * </p>
     */
    public Supply() {
    }

    public Supply id(Long id) {
        this.setId(id);
        return this;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supply withSupplier(Supplier supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Supply withInvoiceNumber(String invoiceNumber) {
        this.setInvoiceNumber(invoiceNumber);
        return this;
    }

    public Instant getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Instant supplyDate) {
        this.supplyDate = supplyDate;
    }

    public Supply withSupplyDate(Instant supplyDate) {
        this.setSupplyDate(supplyDate);
        return this;
    }

    public List<SupplyItem> getSupplyItems() {
        if (this.supplyItems == null) {
            this.supplyItems = new ArrayList<>();
        }
        return Collections.unmodifiableList(supplyItems);
    }

    public void setSupplyItems(List<SupplyItem> supplyItems) {
        this.supplyItems = supplyItems;
    }

    public void addSupplyItem(SupplyItem supplyItem) {
        if (this.supplyItems == null) {
            this.supplyItems = new ArrayList<>();
        }
        supplyItem.setSupply(this);
        supplyItems.add(supplyItem);
    }

    public Supply withSupplyItems(List<SupplyItem> supplyItems) {
        this.setSupplyItems(supplyItems);
        return this;
    }

    public MonetaryAmount getTotal() {
        return total;
    }

    public void setTotal(MonetaryAmount total) {
        this.total = total;
    }

    public Supply withTotal(MonetaryAmount total) {
        this.setTotal(total);
        return this;
    }

    @Override
    public String toString() {
        return "Supply{id=" + getId()
                + ", supplier=" + supplier.getId()
                + ", invoiceNumber=" + invoiceNumber
                + ", supplyDate=" + supplyDate
                + ", total=" + total
                + "}";
    }
}
