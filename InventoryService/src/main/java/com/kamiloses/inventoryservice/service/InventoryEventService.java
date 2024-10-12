package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.EventType;
import com.kamiloses.inventoryservice.dto.InventoryEventDto;
import com.kamiloses.inventoryservice.dto.ProductGetDto;
import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.entity.InventoryEvent;
import com.kamiloses.inventoryservice.repository.InventoryEventRepository;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;


@Slf4j
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
    return findInventoryByName(inventoryEventDto.getInventoryName())
            .flatMap(inventory -> {

                InventoryEvent inventoryEvent = new InventoryEvent();
                inventoryEvent.setInventory(inventory);
                inventoryEvent.setEventTime(new Date().toString());
                inventoryEvent.setEventType(EventType.DELIVERED);
                inventoryEvent.setQuantity(inventoryEventDto.getQuantityChange());
                inventoryEvent.setDescription(inventoryEventDto.getDescription());
                inventoryEvent.setSupplier(supplierService.setSupplierRelatedWithEvent());

                inventory.setAvailableQuantity(inventoryEventDto.getQuantityChange()+inventoryEvent.getQuantity());
           return      inventoryRepository.save(inventory)
                        .then(inventoryEventRepository.save(inventoryEvent))
                        .thenReturn(inventoryEvent);
            })
            .onErrorResume(error -> Mono.error(new InternalServerErrorException("Operation failed")));
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



   // private  Mono<Void> updateInventoryEvent(List<Enve>)






}


