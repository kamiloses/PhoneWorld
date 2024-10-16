package com.kamiloses.productservice;

import com.kamiloses.productservice.rabbit.RabbitConverter;
import com.kamiloses.productservice.rabbit.RabbitMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages ="com.kamiloses.productservice.repository")
@Import({RabbitMQConfig.class, RabbitConverter.class})
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
