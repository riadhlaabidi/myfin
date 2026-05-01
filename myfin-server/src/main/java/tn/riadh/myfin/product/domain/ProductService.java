package tn.riadh.myfin.product.domain;

import org.jmolecules.ddd.annotation.Service;

import tn.riadh.myfin.shared.quantity.UnitOfMesure;

/// Product domain service
@Service
class ProductService {

    private final SellableFormUniquenessChecker uniquenessChecker;

    ProductService(SellableFormUniquenessChecker uniquenessChecker) {
        this.uniquenessChecker = uniquenessChecker;
    }

    Product create(String name, CategoryId categoryId, UnitOfMesure baseUnit,
            FormLabel formLabel, Barcode barcode, PluCode pluCode) {
        validateUniqueness(barcode, pluCode);
        return Product.create(name, categoryId, baseUnit, formLabel, barcode, pluCode);

    }

    void addSellableForm(Product product, FormLabel formLabel, Integer baseUnitQuantity, Barcode barcode,
            PluCode pluCode) {
        validateUniqueness(barcode, pluCode);
        product.addSellableForm(formLabel, baseUnitQuantity, barcode, pluCode);
    }

    private void validateUniqueness(Barcode barcode, PluCode pluCode) {
        if (barcode != null && !uniquenessChecker.isBarcodeUnique(barcode)) {
            throw new BarcodeAlreadyExistsException(barcode);
        }

        if (pluCode != null && !uniquenessChecker.isPluCodeUnique(pluCode)) {
            throw new PluCodeAlreadyExistsException(pluCode);
        }
    }
}
