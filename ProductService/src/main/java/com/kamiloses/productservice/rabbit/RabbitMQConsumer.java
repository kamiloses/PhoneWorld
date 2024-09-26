package com.kamiloses.productservice.rabbit;

import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.productservice.service.ProductService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {

    private  final  RabbitMQProducer rabbitMQProducer;
    private final ProductService productService;

    public RabbitMQConsumer(RabbitMQProducer rabbitMQProducer, ProductService productService) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.productService = productService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_PRODUCT)
    public void receiveMessage(List<ResponseProductInfo> responseProductInfo) {

        productService.modifyProductResponse(responseProductInfo)
                .flatMap(rabbitMQProducer::sendMessage).subscribe();

    }
}
