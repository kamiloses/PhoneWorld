package com.kamiloses.productservice.config;

import com.kamiloses.productservice.entity.Product;
import com.kamiloses.productservice.repository.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

public class ExemplaryProducts {

private final ProductRepository productRepository;

    public ExemplaryProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void exemplaryProducts() {
    Product iPhone1 = new Product("1", "iPhone 14", "Apple", "A2882", "iOS", 6.1, 3279, 12, 6, 128, 799.99);
    Product iPhone2 = new Product("2", "iPhone 13 Pro", "Apple", "A2636", "iOS", 6.1, 3095, 12, 6, 256, 999.99);
    Product iPhone3 = new Product("3", "iPhone 12", "Apple", "A2403", "iOS", 6.1, 2815, 12, 4, 64, 699.00);
    Product iPhone4 = new Product("4", "iPhone 11", "Apple", "A2111", "iOS", 6.1, 3110, 12, 4, 128, 599.99);
    Product iPhone5 = new Product("5", "iPhone SE ", "Apple", "A2595", "iOS", 4.7, 2018, 12, 3, 64, 429.00);
     productRepository.saveAll(List.of(iPhone1, iPhone2, iPhone3, iPhone4, iPhone5)).collectList().block();
}






}
