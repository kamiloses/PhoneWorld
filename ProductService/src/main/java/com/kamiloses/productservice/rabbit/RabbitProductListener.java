package com.kamiloses.productservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.productservice.service.Mapper;
import com.kamiloses.productservice.service.ProductService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitProductListener {

    private  final  RabbitMQProducer rabbitMQProducer;
    private final ProductService productService;
    private final Mapper mapper;
//https://www.appsdeveloperblog.com/micrometer-and-zipkin-in-spring-boot/

    public RabbitProductListener(RabbitMQProducer rabbitMQProducer, ProductService productService, Mapper mapper) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.productService = productService;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_PRODUCT)
    public void receiveMessage(String requestFromOrderService) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
        List<ResponseProductInfo>     productInfoList = objectMapper.readValue(requestFromOrderService, new TypeReference<List<ResponseProductInfo>>() {});
            List<ResponseProductInfo> products = productService.getProductPrice(productInfoList).block();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

//        rabbitMQProducer.sendMessageToInventory(mapper.productInfoToInventoryInfo(products));

//          Mono<List<ResponseInventoryInfo>> requestToInventoryModule = mapper.productInfoToInventoryInfo(responseProductInfo).collectList();
//
//        rabbitMQProducer.sendMessageToInventory(requestToInventoryModule.block());
//        productService.modifyProductResponse(responseProductInfo)
//                .flatMap(rabbitMQProducer::sendMessage).subscribe();

    }
}
