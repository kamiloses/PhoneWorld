package com.kamiloses.productservice.rabbit;

import com.kamiloses.productservice.dto.ResponseInventoryInfo;
import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.productservice.service.Mapper;
import com.kamiloses.productservice.service.ProductService;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {

    private  final  RabbitMQProducer rabbitMQProducer;
    private final ProductService productService;
    private final Mapper mapper;
//https://www.appsdeveloperblog.com/micrometer-and-zipkin-in-spring-boot/

    public RabbitMQConsumer(RabbitMQProducer rabbitMQProducer, ProductService productService, Mapper mapper) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.productService = productService;
        this.mapper = mapper;
    }
     //todo doać parametr łącznego sumy pieniedzy kupująćego, jeżeli ma mniej pieniedzy niż getProductPrice wszystkich produktów to zakóp usuwamy
    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_PRODUCT)
    public void receiveMessage(List<ResponseProductInfo> responseProductInfo) {


        List<ResponseProductInfo> products = productService.getProductPrice(responseProductInfo).block();
        System.out.println("wszystko "+products);

        rabbitMQProducer.sendMessageToInventory(mapper.productInfoToInventoryInfo(products));

//          Mono<List<ResponseInventoryInfo>> requestToInventoryModule = mapper.productInfoToInventoryInfo(responseProductInfo).collectList();
//
//        rabbitMQProducer.sendMessageToInventory(requestToInventoryModule.block());
//        productService.modifyProductResponse(responseProductInfo)
//                .flatMap(rabbitMQProducer::sendMessage).subscribe();

    }
}
