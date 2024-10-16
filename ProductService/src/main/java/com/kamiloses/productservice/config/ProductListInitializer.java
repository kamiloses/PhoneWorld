package com.kamiloses.productservice.config;

import com.kamiloses.productservice.entity.Product;
import com.kamiloses.productservice.repository.ProductRepository;
import jakarta.annotation.PostConstruct;

import java.util.List;

public class ProductListInitializer {

private final ProductRepository productRepository;

    public ProductListInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

   @PostConstruct
    public void exemplaryProducts() {
        if (!productRepository.existsById("1").block()) {
            Product iPhone1 = new Product("1", "iPhone 14", "Apple", 799.99);
            Product iPhone2 = new Product("2", "iPhone 13 Pro", "Apple", 999.99);
            Product iPhone3 = new Product("3", "iPhone 12", "Apple", 699.00);
            Product iPhone4 = new Product("4", "iPhone 11", "Apple", 599.99);
            Product iPhone5 = new Product("5", "iPhone SE ", "Apple", 429.00);
            productRepository.saveAll(List.of(iPhone1, iPhone2, iPhone3, iPhone4, iPhone5)).collectList().block();
        }
    }






}
