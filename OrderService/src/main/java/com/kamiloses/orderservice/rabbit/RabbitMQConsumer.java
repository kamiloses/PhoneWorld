package com.kamiloses.orderservice.rabbit;

import com.kamiloses.orderservice.dto.FullOrderDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitMQConsumer {


    private List<FullOrderDetailsDto> fullOrderDetailsDtoList;
    private String accountBalance;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PRODUCT_TO_ORDER)
    public void receiveMessage(List<FullOrderDetailsDto> fullOrderDetailsDtoList) {
        log.info("Received message from product service: {}", fullOrderDetailsDtoList);
        System.err.println(fullOrderDetailsDtoList);
        this.fullOrderDetailsDtoList = fullOrderDetailsDtoList;
    }

    public List<FullOrderDetailsDto> getModifiedResponseFromProductService() {
        return fullOrderDetailsDtoList;
    }


}
