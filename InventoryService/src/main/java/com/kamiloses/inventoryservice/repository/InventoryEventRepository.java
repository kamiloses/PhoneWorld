package com.kamiloses.inventoryservice.repository;

import com.kamiloses.inventoryservice.entity.InventoryEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface InventoryEventRepository extends ReactiveMongoRepository<InventoryEvent, String> {
}
