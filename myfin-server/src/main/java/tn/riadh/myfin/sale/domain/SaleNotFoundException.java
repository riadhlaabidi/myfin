package tn.riadh.myfin.sale.domain;

public class SaleNotFoundException extends RuntimeException {

    public SaleNotFoundException(String message) {
        super(message);
    }

    public static SaleNotFoundException byId(SaleId saleId) {
        return new SaleNotFoundException("Sale with id " + saleId + " was not found");
    }
}
