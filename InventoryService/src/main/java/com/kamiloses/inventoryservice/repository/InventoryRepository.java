package com.kamiloses.inventoryservice.repository;

import com.kamiloses.inventoryservice.entity.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {

    Flux<Inventory> findByProductIdIn(List<String> productIds);
    Mono<Inventory> findByProductId(String productId);

}
