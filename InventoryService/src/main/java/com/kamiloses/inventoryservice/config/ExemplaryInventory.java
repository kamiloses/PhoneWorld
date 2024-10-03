package com.kamiloses.inventoryservice.config;

import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExemplaryInventory {

    private final InventoryRepository inventoryRepository;

    public ExemplaryInventory(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void exemplaryInventory() {
//        Inventory inventory1 = new Inventory("1","1",50,0);
//        Inventory inventory2 = new Inventory("2","2",50,0);
//        Inventory inventory3 = new Inventory("3","3",50,0);
//        Inventory inventory4 = new Inventory("4","4",50,0);
//        Inventory inventory5 = new Inventory("5","5",50,0);
//        inventoryRepository.saveAll(List.of(inventory1,inventory2,inventory3,inventory4,inventory5)).collectList().block();
//}








}
