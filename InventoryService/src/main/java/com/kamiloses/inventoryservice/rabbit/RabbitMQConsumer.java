package com.kamiloses.inventoryservice.rabbit;

import com.kamiloses.inventoryservice.dto.ResponseInventoryInfo;
import com.kamiloses.inventoryservice.service.InventoryService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {

    private  final RabbitMQProducer rabbitMQProducer;
    private final InventoryService inventoryService;

    public RabbitMQConsumer(RabbitMQProducer rabbitMQProducer, InventoryService inventoryService) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.inventoryService = inventoryService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PRODUCT_TO_INVENTORY)
    public void receiveMessage(List<ResponseInventoryInfo> responseInventoryInfo) {
     inventoryService.responseIfProductAvailable(responseInventoryInfo).subscribe();
    }
}
