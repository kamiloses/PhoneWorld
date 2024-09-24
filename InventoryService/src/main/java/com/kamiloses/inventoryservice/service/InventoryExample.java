//package com.kamiloses.inventoryservice.service;
//
//import com.kamiloses.inventoryservice.entity.Inventory;
//import com.kamiloses.inventoryservice.repository.InventoryRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Service;
//
//@Service
//public class InventoryExample {
//private final InventoryRepository inventoryRepository;
//
//    public InventoryExample(InventoryRepository inventoryRepository) {
//        this.inventoryRepository = inventoryRepository;
//    }
//     @PostConstruct
//    public void a() {
//    Inventory inventory = new Inventory();
//
//    // Ustawianie wartości pól
//    inventory.setId("10");
//    inventory.setProductId("1");
//    inventory.setAvailableQuantity(100);
//    inventory.setReservedQuantity(20);
//    inventory.setSoldQuantity(50);
//   inventoryRepository.save(inventory).block();
//        System.out.println("zapisało");
//}
//}