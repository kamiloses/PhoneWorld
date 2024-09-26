package com.kamiloses.productservice.rabbit;

import com.kamiloses.productservice.dto.ResponseInventoryInfo;
import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.productservice.service.Mapper;
import com.kamiloses.productservice.service.ProductService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {

    private  final  RabbitMQProducer rabbitMQProducer;
    private final ProductService productService;
    private final Mapper mapper;

    public RabbitMQConsumer(RabbitMQProducer rabbitMQProducer, ProductService productService, Mapper mapper) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.productService = productService;
        this.mapper = mapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_PRODUCT)
    public void receiveMessage(List<ResponseProductInfo> responseProductInfo) {
        Mono<List<ResponseInventoryInfo>> requestToInventoryModule = mapper.productInfoToInventoryInfo(responseProductInfo).collectList();

        rabbitMQProducer.sendMessageToInventory(requestToInventoryModule).subscribe();
        productService.modifyProductResponse(responseProductInfo)
                .flatMap(rabbitMQProducer::sendMessage).subscribe();

    }
}
