package tn.riadh.myfin.product.application;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.riadh.myfin.product.domain.Product;
import tn.riadh.myfin.product.domain.ProductName;
import tn.riadh.myfin.product.domain.ProductRepository;
import tn.riadh.myfin.product.domain.SellableForm;
import tn.riadh.myfin.shared.quantity.UnitOfMesure;

public class CreateProductService {
    private final Logger logger = LoggerFactory.getLogger(CreateProductService.class);
    private final ProductRepository productRepository;

    public CreateProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductName productName, UnitOfMesure baseUnit, Set<SellableForm> sellablesForms) {
    }
}
