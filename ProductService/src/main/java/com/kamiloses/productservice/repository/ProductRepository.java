package com.kamiloses.productservice.repository;

import com.kamiloses.productservice.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> getProductsByName(String name);
}
