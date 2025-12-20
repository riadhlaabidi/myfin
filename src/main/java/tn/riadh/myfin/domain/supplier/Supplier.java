package tn.riadh.myfin.domain.supplier;

import tn.riadh.myfin.domain.common.AbstractEntity;

/**
 * Represents a supplier entity.
 * <p>
 * Identity and equality behavior are inherited from {@link AbstractEntity}.
 * </p>
 */
public class Supplier extends AbstractEntity {

    private String name;
    private String address;
    private String phoneNumber;
    private String tin;

    /**
     * Creates an empty {@code Supplier} instance.
     * <p>
     * Required for frameworks that rely on a no-argument constructor.
     * </p>
     */
    public Supplier() {
    }

    public Supplier id(Long id) {
        this.setId(id);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier withName(String name) {
        this.setName(name);
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Supplier withAddress(String address) {
        this.setAddress(address);
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Supplier withPhoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public Supplier withTin(String tin) {
        this.setTin(tin);
        return this;
    }

    @Override
    public String toString() {
        return "Supplier{id=" + getId()
                + ", name=" + name
                + ", address=" + address
                + ", phoneNumber" + phoneNumber
                + ", tin" + tin
                + "}";
    }
}
