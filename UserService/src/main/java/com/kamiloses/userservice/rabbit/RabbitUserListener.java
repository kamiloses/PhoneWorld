package com.kamiloses.userservice.rabbit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.userservice.dto.UserDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitUserListener {

private String userDetails;

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