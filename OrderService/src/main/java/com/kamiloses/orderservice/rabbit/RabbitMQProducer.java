package com.kamiloses.orderservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.orderservice.dto.ResponseProductInfo;
import com.kamiloses.orderservice.dto.UserDto;
import lombok.Getter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    private UserDto userDetails;



    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessageToProductService(String requestToProductService) {
        System.out.println("wiadomosc"+requestToProductService);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_ORDER_TO_PRODUCT, requestToProductService);
    }




    public void sendMessageToUserService() {


        String responseFromUserModule = (String) rabbitTemplate.convertSendAndReceive(RabbitMQConfig.EXCHANGE_MAKING_ORDER, RabbitMQConfig.ROUTING_KEY_ORDER_TO_USER, "");

       //sending message to userService and receiving logged user details

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userDetails = objectMapper.readValue(responseFromUserModule, UserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }


}