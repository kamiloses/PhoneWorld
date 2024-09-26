package com.kamiloses.productservice.rabbit;

import com.kamiloses.productservice.dto.ResponseInventoryInfo;
import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Mono<Void> sendMessage(List<ResponseProductInfo> responseForProductsFromProductService) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_ORDER, responseForProductsFromProductService);
        return Mono.empty();
    }
    public Mono<Void> sendMessageToInventory(Mono<List<ResponseInventoryInfo>> responseForInventoryDetails) {

        return responseForInventoryDetails.flatMap(inventoryDetails -> {
            System.out.println("abcds"+inventoryDetails);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER,
                    RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_INVENTORY,
                    inventoryDetails);
            return Mono.empty();
        });
    }
}