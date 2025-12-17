package tn.riadh.myfin.domain;

public class Supplier extends AbstractEntity {
    private String name;
    private String address;
    private String phoneNumber;
    private String tin;

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTin() {
        return tin;
    }

    public void setTin(String tin) {
        this.tin = tin;
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
