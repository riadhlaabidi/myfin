package tn.riadh.myfin.core.product.repository;

import java.util.Optional;

import tn.riadh.myfin.core.product.domain.Product;
import tn.riadh.myfin.core.product.domain.ProductId;

public interface ProductRepository {
    void save(Product product);

    Optional<Product> findById(ProductId productId);
}
