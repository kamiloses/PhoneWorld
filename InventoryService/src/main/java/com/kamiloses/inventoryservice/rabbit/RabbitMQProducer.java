package com.kamiloses.inventoryservice.rabbit;

import com.kamiloses.inventoryservice.dto.ResponseInventoryInfo;
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


    public Mono<Void> sendMessageToOrder(Mono<List<ResponseInventoryInfo>> responseForInventoryDetails) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_INVENTORY, responseForInventoryDetails);
        return Mono.empty();
    }


}