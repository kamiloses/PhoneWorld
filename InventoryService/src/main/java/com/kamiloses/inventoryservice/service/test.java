package com.kamiloses.inventoryservice.service;//package com.kamiloses.inventoryservice.service;
//
//import com.kamiloses.inventoryservice.entity.Inventory;
//import com.kamiloses.inventoryservice.entity.InventoryEvent;
//import com.kamiloses.inventoryservice.entity.Product;
//import com.kamiloses.inventoryservice.repository.InventoryEventRepository;
//import com.kamiloses.inventoryservice.repository.InventoryRepository;
//import com.kamiloses.inventoryservice.repository.ProductRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//@Service
//public class test {
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private InventoryRepository inventoryRepository;
//
//    @Autowired
//    private InventoryEventRepository inventoryEventRepository;
//
//
//    @PostConstruct
//    public void saveEntities() {
//        // Tworzenie obiektu Product
//        Product product = new Product();
//        product.setId("1");
//        product.setName("Smartphone X");
//        product.setManufacturer("TechCorp");
//        product.setModel("X123");
//        product.setOperatingSystem("Android 11");
//        product.setScreenSize(6.5);
//        product.setBatteryCapacity(4000);
//        product.setCameraResolution(48);
//        product.setRam(8);
//        product.setStorageCapacity(128);
//        product.setPrice(699.99);
//
//        // Zapisanie produktu
//        productRepository.save(product).block();
//
//        // Tworzenie obiektu Inventory
//        Inventory inventory = new Inventory();
//        inventory.setId("1");
//        inventory.setAvailableQuantity(100);
//        inventory.setReservedQuantity(20);
//        inventory.setSoldQuantity(30);
//        inventory.setProduct(product); // Ustalanie referencji do produktu
//
//        // Zapisanie inwentarza
//        inventoryRepository.save(inventory).block();
//
//        // Tworzenie obiektu InventoryEvent
//        InventoryEvent inventoryEvent = new InventoryEvent();
//        inventoryEvent.setId("1");
//        inventoryEvent.setInventory(inventory); // Ustalanie referencji do inwentarza
//        inventoryEvent.setEventTime(LocalDateTime.now());
//        inventoryEvent.setEventType("ADD");
//        inventoryEvent.setQuantityChange(50);
//        inventoryEvent.setDescription("Added 50 units to inventory.");
//
//        // Zapisanie zdarzenia inwentarza
//        inventoryEventRepository.save(inventoryEvent).block();
//    }
//
//
//
//}
