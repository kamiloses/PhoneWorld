package com.kamiloses.orderservice.rabbit;

import com.kamiloses.orderservice.dto.ResponseProductInfo;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToProductService(List<ResponseProductInfo> requestForProductPrice) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_ORDER_TO_PRODUCT, requestForProductPrice);
        log.info("Sent message: {}", requestForProductPrice);
    }


}