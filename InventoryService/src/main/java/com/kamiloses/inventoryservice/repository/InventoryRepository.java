package com.kamiloses.inventoryservice.repository;

import com.kamiloses.inventoryservice.entity.Inventory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InventoryRepository extends ReactiveMongoRepository<Inventory, String> {
}
