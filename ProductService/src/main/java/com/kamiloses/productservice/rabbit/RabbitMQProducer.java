package com.kamiloses.productservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.productservice.dto.FullOrderDetailsDto;
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

    public Mono<Void> sendMessage(List<FullOrderDetailsDto> responseForProductsFromProductService) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_ORDER, responseForProductsFromProductService);
        return Mono.empty();
    }


    public void sendMessageToInventory(List<FullOrderDetailsDto> fullOrderDetailsDto) {
        System.out.println("inventory "+fullOrderDetailsDto);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String mappedOrderDetails = objectMapper.writeValueAsString(fullOrderDetailsDto);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_PRODUCT_TO_INVENTORY, mappedOrderDetails);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

}
}