package com.kamiloses.inventoryservice.router;

import com.kamiloses.inventoryservice.dto.InventoryEventDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class InventoryRouterTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");



    @Autowired
    private WebTestClient webTestClient;



    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();

    }

//    @BeforeEach
//    void initProducts() {
//        productRepository.deleteAll().block();
//
//        List<Product> products = List.of(
//                new Product(null, "Smartphone A", "Manufacturer A", "Model A", "Android", 6.5, 4000, 12, 4, 64, 299.99),
//                new Product(null, "Smartphone B", "Manufacturer B", "Model B", "iOS", 6.1, 3500, 12, 6, 128, 999.99),
//                new Product(null, "Smartphone C", "Manufacturer C", "Model C", "Android", 6.7, 4500, 48, 8, 256, 799.99)
//        );
//
//        productRepository.saveAll(products).collectList().block();
//    }





    @Test
    public void shouldCheckGetAllPhonesMethod() {
        InventoryEventDto inventoryEventDto = new InventoryEventDto();
        inventoryEventDto.setInventoryName("iPhone 13 Pro");
        inventoryEventDto.setQuantityChange(30);
        webTestClient.post().uri("/api/makeADeliver")
                      .bodyValue(inventoryEventDto).exchange().expectStatus().isOk();




    }

    @AfterAll
    static void tearDown() {

        mongoDBContainer.stop();

    }


}