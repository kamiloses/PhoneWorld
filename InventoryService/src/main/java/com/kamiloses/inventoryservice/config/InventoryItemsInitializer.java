package com.kamiloses.inventoryservice.config;

import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryItemsInitializer {

    private final InventoryRepository inventoryRepository;

    public InventoryItemsInitializer(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PostConstruct
    public void inventory() {
        if (!inventoryRepository.existsById("1").block()) {
            Inventory inventory1 = new Inventory("1", "1", 150, 0);
            Inventory inventory2 = new Inventory("2", "2", 100, 0);
            Inventory inventory3 = new Inventory("3", "3", 50, 0);
            Inventory inventory4 = new Inventory("4", "4", 83, 0);
            Inventory inventory5 = new Inventory("5", "5", 64, 0);
            inventoryRepository.saveAll(List.of(inventory1, inventory2, inventory3, inventory4, inventory5)).collectList().block();
        }
        }








}
