package com.kamiloses.orderservice;

import com.kamiloses.rabbitmqconfig.RabbitConverter;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RabbitMQConfig.class, RabbitConverter.class})
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
