package com.kamiloses.userservice.rabbit;

import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RabbitConsumer {
private final RabbitProducer rabbitProducer;

    public RabbitConsumer(RabbitProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_USER)
    public void receiveMessage() {
        double accountBalance = 5000;
        rabbitProducer.sendAccountBalanceToOrderService(accountBalance);

    }


    }
