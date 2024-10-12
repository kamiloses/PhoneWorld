package com.kamiloses.inventoryservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.inventoryservice.dto.FullOrderDetailsDto;
import com.kamiloses.inventoryservice.service.InventoryService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitInventoryListener {

    private  final RabbitMQProducer rabbitMQProducer;
    private final InventoryService inventoryService;

    public RabbitInventoryListener(RabbitMQProducer rabbitMQProducer, InventoryService inventoryService) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.inventoryService = inventoryService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PRODUCT_TO_INVENTORY)
    public void receiveMessage(String fullOrderDetailsDto) {
        System.out.println("String "+fullOrderDetailsDto);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<FullOrderDetailsDto> mappedFullOrder = objectMapper.readValue(fullOrderDetailsDto, new TypeReference<List<FullOrderDetailsDto>>() {});
             inventoryService.responseIfProductAvailable(mappedFullOrder).subscribe();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
