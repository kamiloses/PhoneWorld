package com.kamiloses.userservice;

import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendAccountBalanceToOrderService(double accountBalance) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER,
                RabbitMQConfig.ROUTING_KEY_USER_TO_ORDER,
                accountBalance);

    }


}