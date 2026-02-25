package tn.riadh.myfin.product.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tn.riadh.myfin.product.domain.ProductName;
import tn.riadh.myfin.product.domain.ProductRepository;

public class CreateProductService {
    private final Logger logger = LoggerFactory.getLogger(CreateProductService.class);
    private final ProductRepository productRepository;

    public CreateProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void createProduct(ProductName productName, ) {

    }
}
