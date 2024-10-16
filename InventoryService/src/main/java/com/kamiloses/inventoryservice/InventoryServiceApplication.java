package com.kamiloses.inventoryservice;

import com.kamiloses.inventoryservice.rabbit.RabbitConverter;
import com.kamiloses.inventoryservice.rabbit.RabbitMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.kamiloses.inventoryservice.repository")
//@Import({RabbitMQConfig.class, RabbitConverter.class})
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

}
