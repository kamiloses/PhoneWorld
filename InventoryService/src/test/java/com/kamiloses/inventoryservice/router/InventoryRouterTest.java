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