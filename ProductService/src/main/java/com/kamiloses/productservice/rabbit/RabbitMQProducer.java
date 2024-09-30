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


    public void sendMessageToInventory(List<ResponseInventoryInfo> responseForInventoryDetails) {
        System.out.println("lista "+responseForInventoryDetails);
        ResponseInventoryInfo responseInventoryInfo = new ResponseInventoryInfo();
        responseInventoryInfo.setProductId("100");
        responseInventoryInfo.setProductName("Smartphone X");
        responseInventoryInfo.setProductQuantity(1);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER,
                    RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_INVENTORY,
                    responseInventoryInfo);

    }
}