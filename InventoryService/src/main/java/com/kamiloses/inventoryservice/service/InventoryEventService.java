package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.EventType;
import com.kamiloses.inventoryservice.dto.ProductGetDto;
import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.entity.InventoryEvent;
import com.kamiloses.inventoryservice.repository.InventoryEventRepository;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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


        public Mono<InventoryEvent> addPhoneToWereHouse(InventoryEventDto inventoryEventDto) {
        InventoryEvent event = new InventoryEvent();
        event.setInventory(findInventoryByName(inventoryEventDto.getInventoryName()));
        event.setEventTime(new Date().toString()); //todo zamień
        event.setEventType(EventType.DELIVERED);
        event.setQuantity(findInventoryByName(inventoryEventDto.getInventoryName()).getAvailableQuantity()+inventoryEventDto.getQuantityChange());
        event.setDescription(inventoryEventDto.getDescription());
        event.setSupplier(supplierService.setSupplierRelatedWithEvent());
return Mono.just(event);

    }

//    private void removePhoneFromWereHouse(InventoryEventDto inventoryEventDto) {
//        InventoryEvent event = new InventoryEvent();
//        event.setInventory(findInventoryByName(inventoryEventDto.getInventoryName()));
//        event.setEventTime(new Date().toString());
//        event.setEventType(EventType.DELIVERED);
//        event.setQuantity(findInventoryByName(inventoryEventDto.getInventoryName()).getAvailableQuantity()+inventoryEventDto.getQuantityChange());
//        event.setDescription(inventoryEventDto.getDescription());
//        event.setSupplier(supplierService.setSupplierRelatedWithEvent());
//
//    }


    private Inventory findInventoryByName(String name) {
        List<String> phonesId = webClient.get()
                .uri("/api/products")
                .retrieve()
                .bodyToFlux(ProductGetDto.class)
                .filter(product -> product.getName().equals(name)).map(ProductGetDto::getId).collectList().block();

        return inventoryRepository.findByProductIdIn(phonesId)
                .singleOrEmpty()
                .switchIfEmpty(Mono.error(new PhoneNotFoundException("This phone was not found")))
                .block();




    }


}

