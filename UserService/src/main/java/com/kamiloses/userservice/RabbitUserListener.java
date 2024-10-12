package com.kamiloses.userservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitUserListener {
private final RabbitProducer rabbitProducer;

private String userDetails;
    public RabbitUserListener(RabbitProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ORDER_TO_USER)
    public String receiveMessage(String invoker) {
        UserDto userDto = new UserDto();
        userDto.setEmail("kowalski@gmail.com");
         userDto.setAccountBalance(100000.0);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            userDetails = objectMapper.writeValueAsString(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return userDetails;
    }




    }