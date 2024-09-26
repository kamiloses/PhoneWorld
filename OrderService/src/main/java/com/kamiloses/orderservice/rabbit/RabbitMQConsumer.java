package com.kamiloses.orderservice.rabbit;

import com.kamiloses.orderservice.dto.ResponseProductInfo;
import com.kamiloses.rabbitmqconfig.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {


    private List<ResponseProductInfo> responseProductInfoList;

 @RabbitListener(queues = RabbitMQConfig.QUEUE_PRODUCT_TO_ORDER)
    public void receiveMessage(List<ResponseProductInfo> responseProductInfoList) {
        log.info("Received message from product service: {}", responseProductInfoList);

        this.responseProductInfoList = responseProductInfoList;
    }

 public List<ResponseProductInfo> getModifiedResponseFromProductService(){

        return responseProductInfoList;
 }



}
