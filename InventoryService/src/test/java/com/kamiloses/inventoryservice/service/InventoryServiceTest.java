package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.dto.FullOrderDetailsDto;
import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;



@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository inventoryRepository;

    @Test
    void testResponseIfProductAvailable_Success() {
        FullOrderDetailsDto orderDetails1 = new FullOrderDetailsDto("1", "iPhone 14", 5, 20.0, 100.0);
        FullOrderDetailsDto orderDetails2 = new FullOrderDetailsDto("2", "iPhone 13 Pro", 3, 10.0, 50.0);
        List<FullOrderDetailsDto> orderList = List.of(orderDetails1, orderDetails2);

        Inventory inventory1 = new Inventory("1", "1", 50, 10);
        Inventory inventory2 = new Inventory("2", "2", 100, 10);

        Mockito.when(inventoryRepository.findByProductId("1")).thenReturn(Mono.just(inventory1));
        Mockito.when(inventoryRepository.findByProductId("2")).thenReturn(Mono.just(inventory2));
        Mockito.when(inventoryRepository.saveAll(List.of(inventory1,inventory2))).thenReturn(Flux.just(inventory1, inventory2));

        Mono<Void> result = inventoryService.responseIfProductAvailable(orderList);



        StepVerifier.create(result)
                .expectSubscription()
                .verifyComplete();


    }






    @Test//all products are unavailable
    void testResponseIfProductAvailable_Fail() {
        FullOrderDetailsDto orderDetails1 = new FullOrderDetailsDto("1", "iPhone 14", 500, 20.0, 100.0);
        FullOrderDetailsDto orderDetails2 = new FullOrderDetailsDto("2", "iPhone 13 Pro", 300, 10.0, 50.0);
        List<FullOrderDetailsDto> orderList = List.of(orderDetails1, orderDetails2);
//       added  more product than available in the inventory
        Inventory inventory1 = new Inventory("1", "1", 50, 10);
        Inventory inventory2 = new Inventory("2", "2", 100, 10);

               Mockito.when(inventoryRepository.findByProductId("1")).thenReturn(Mono.just(inventory1));
        Mockito.when(inventoryRepository.findByProductId("2")).thenReturn(Mono.just(inventory2));
        Mockito.when(inventoryRepository.saveAll(List.of(inventory1,inventory2))).thenReturn(Flux.just(inventory1, inventory2));

        Mono<Void> result = inventoryService.responseIfProductAvailable(orderList);

        StepVerifier.create(result)
                .expectError(ProductUnavailableException.class)
                .verify();

    }


    @Test// only one product is not available
    void testResponseIfProductAvailable_Fail_OneProduct() {
        FullOrderDetailsDto orderDetails1 = new FullOrderDetailsDto("1", "iPhone 14", 1, 20.0, 100.0);
        FullOrderDetailsDto orderDetails2 = new FullOrderDetailsDto("2", "iPhone 13 Pro", 300, 10.0, 50.0);
        List<FullOrderDetailsDto> orderList = List.of(orderDetails1, orderDetails2);
//       added  more product than available in the inventory
        Inventory inventory1 = new Inventory("1", "1", 50, 10);
        Inventory inventory2 = new Inventory("2", "2", 100, 10);

        Mockito.when(inventoryRepository.findByProductId("1")).thenReturn(Mono.just(inventory1));
        Mockito.when(inventoryRepository.findByProductId("2")).thenReturn(Mono.just(inventory2));
        Mockito.when(inventoryRepository.saveAll(List.of(inventory1,inventory2))).thenReturn(Flux.just(inventory1, inventory2));

        Mono<Void> result = inventoryService.responseIfProductAvailable(orderList);

        StepVerifier.create(result)
                .expectError(ProductUnavailableException.class)
                .verify();

    }

}