package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.EventType;
import com.kamiloses.inventoryservice.dto.InventoryEventDto;
import com.kamiloses.inventoryservice.dto.ProductGetDto;
import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.entity.InventoryEvent;
import com.kamiloses.inventoryservice.repository.InventoryEventRepository;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;


@Service
public class InventoryEventService {
    private final InventoryEventRepository inventoryEventRepository;
    private final InventoryRepository inventoryRepository;
    private final SupplierService supplierService;
    private WebClient webClient;

    @PostConstruct
    private void init() {
        webClient = WebClient.create("http://localhost:8080");
    }

    public InventoryEventService(InventoryEventRepository inventoryEventRepository, InventoryRepository inventoryRepository, SupplierService supplierService) {
        this.inventoryEventRepository = inventoryEventRepository;
        this.inventoryRepository = inventoryRepository;
        this.supplierService = supplierService;
    }


//    public Mono<InventoryEvent> addPhoneToWereHouse(InventoryEventDto inventoryEventDto) {
//        //    Mono<Inventory> abc = findInventoryByName("abc");
//        InventoryEvent event = new InventoryEvent();
//        event.setInventory(findInventoryByName(inventoryEventDto.getInventoryName()).block());
//        event.setEventTime(new Date().toString()); //todo zamień
//        event.setEventType(EventType.DELIVERED);
//        //   event.setQuantity(findInventoryByName(inventoryEventDto.getInventoryName()).block().getAvailableQuantity()+inventoryEventDto.getQuantityChange());
//        event.setDescription(inventoryEventDto.getDescription());
//        event.setSupplier(supplierService.setSupplierRelatedWithEvent());
//        inventoryEventRepository.save(event).subscribe();
//        return Mono.just(event);}
public Mono<InventoryEvent> addPhoneToWereHouse(InventoryEventDto inventoryEventDto) {
    return findInventoryByName(inventoryEventDto.getInventoryName())
            .flatMap(inventory -> {
                System.out.println("zapisało1"+inventory);

                InventoryEvent inventoryEvent = new InventoryEvent();
                inventoryEvent.setInventory(inventory);
                inventoryEvent.setEventTime(new Date().toString());
                inventoryEvent.setEventType(EventType.DELIVERED);
                inventoryEvent.setQuantity(inventory.getAvailableQuantity() + inventoryEventDto.getQuantityChange());
                inventoryEvent.setDescription(inventoryEventDto.getDescription());
                inventoryEvent.setSupplier(supplierService.setSupplierRelatedWithEvent());
                System.out.println("zapisało2"+inventory);
                return inventoryEventRepository.save(inventoryEvent)
                        .thenReturn(inventoryEvent);
            })
            .doOnError(error -> {
                System.out.println("Error occurred: " + error.getMessage());
            });
}











    private Mono<Inventory> findInventoryByName(String name) {
        return webClient.get()
                .uri("/api/products")
                .retrieve()
                .bodyToFlux(ProductGetDto.class)
                .filter(product -> product.getName().equals("Smartphone X"))
                .map(ProductGetDto::getId)
                .single()
                .switchIfEmpty(Mono.error(new PhoneNotFoundException("This phone was not found")))
                .flatMap(inventoryRepository::findByProductId);

    }


//    @PostConstruct
//    private void findInventoryByNamex() {
//        String name = "Smartphone X";
//        Mono<Inventory> phoneId = webClient.get()
//                .uri("/api/products")
//                .retrieve()
//                .bodyToFlux(ProductGetDto.class)
//                .filter(product -> product.getName().equals("Smartphone X"))
//                .map(ProductGetDto::getId)
//                .single()
//                .switchIfEmpty(Mono.error(new PhoneNotFoundException("This phone was not found")))
//                .flatMap(inventoryRepository::findByProductId);
//        System.out.println("komunikat"+phoneId.block());
//
//    }


}


