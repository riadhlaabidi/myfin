package tn.riadh.myfin.product.domain;

import java.util.Optional;

public interface ProductRepository {
    void save(Product product);

    Optional<Product> findById(ProductId productId);

    Optional<Product> findByBarcode(Barcode barcode);
}
