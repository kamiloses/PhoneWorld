package com.kamiloses.inventoryservice.repository;

import com.kamiloses.inventoryservice.dto.ProductDto;
import com.kamiloses.inventoryservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> getProductsByName(String name);
}
