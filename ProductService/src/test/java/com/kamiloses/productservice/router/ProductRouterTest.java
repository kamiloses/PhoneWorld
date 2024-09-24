package com.kamiloses.productservice.router;

import com.kamiloses.productservice.dto.ProductDto;
import com.kamiloses.productservice.entity.Product;
import com.kamiloses.productservice.repository.ProductRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;


@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class ProductRouterTest {

    @Container
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");



    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private  ProductRepository productRepository;


    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();

    }

    @BeforeEach
    void initProducts() {
        productRepository.deleteAll().block();

        List<Product> products = List.of(
                new Product(null, "Smartphone A", "Manufacturer A", "Model A", "Android", 6.5, 4000, 12, 4, 64, 299.99),
                new Product(null, "Smartphone B", "Manufacturer B", "Model B", "iOS", 6.1, 3500, 12, 6, 128, 999.99),
                new Product(null, "Smartphone C", "Manufacturer C", "Model C", "Android", 6.7, 4500, 48, 8, 256, 799.99)
        );

        productRepository.saveAll(products).collectList().block();
    }





    @Test
    public void shouldCheckGetAllPhonesMethod() {



        webTestClient.get().uri("/api/products")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(ProductDto.class)
                .hasSize(3);




    }

    @AfterAll
    static void tearDown() {

        mongoDBContainer.stop();

    }


}