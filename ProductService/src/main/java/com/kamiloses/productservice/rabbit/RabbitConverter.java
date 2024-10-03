//package com.kamiloses.productservice.rabbit;
//
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.amqp.support.converter.SimpleMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitConverter {
//
////    @Bean
////    public MessageConverter jsonMessageConverter() {
////        return new Jackson2JsonMessageConverter();
////    }
////    @Bean
////    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
////        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
////        rabbitTemplate.setMessageConverter(jsonMessageConverter());
////        return rabbitTemplate;
////    }
//
//
//
//    @Bean
//    public MessageConverter simpleMessageConverter() {
//        return new SimpleMessageConverter();
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(simpleMessageConverter());
//        return rabbitTemplate;
//    }
//
//
//
//}
