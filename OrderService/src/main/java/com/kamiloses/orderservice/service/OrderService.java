package com.kamiloses.orderservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kamiloses.orderservice.dto.MakeAnOrderDto;
import com.kamiloses.orderservice.dto.FullOrderDetailsDto;
import com.kamiloses.orderservice.entity.Order;
import com.kamiloses.orderservice.rabbit.RabbitMQConsumer;
import com.kamiloses.orderservice.rabbit.RabbitMQProducer;
import com.kamiloses.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final Mapper mapper;
    private final RabbitMQProducer rabbitMQProducer;
    private final RabbitMQConsumer rabbitMQConsumer;


    public OrderService(OrderRepository orderRepository, Mapper mapper, RabbitMQProducer rabbitMQProducer, RabbitMQConsumer rabbitMQConsumer) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQConsumer = rabbitMQConsumer;
    }


    public Mono<Void> makeAnOrder(MakeAnOrderDto makeAnOrderDto)  {

        rabbitMQProducer.sendMessageToUserService();
       // fetching accountBalance from userService
        System.err.println("userDetails"+rabbitMQProducer.getUserDetails());


     List<FullOrderDetailsDto> requestToProductService = mapper.OrderItemDtoToResponseProductInfo(makeAnOrderDto.getOrderItems(),rabbitMQProducer.getUserDetails().getAccountBalance());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String  mappedRequestToProductService  = objectMapper.writeValueAsString(requestToProductService);
            rabbitMQProducer.sendMessageToProductService(mappedRequestToProductService);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }//request to productService to ensure if user's account balance is enough to buy the products



//        List<ResponseProductInfo> responseFromProductService = rabbitMQConsumer.getModifiedResponseFromProductService();
  //        request and response about product price
//
//        Order order = mapper.makeAnOrderDtoToOrder(makeAnOrderDto);
//        System.err.println("Wynik" + responseFromProductService);
//        order.setOrderItems(mapper.responseProductInfoToOrderItem(responseFromProductService));
//        System.out.println("Wynik" + order);

        return Mono.empty();
       // return orderRepository.save(order).then();

    }

    //   GGET /api/orders/customer/{customerId} – Pobranie zamówień klienta
    public Flux<Order> getOrdersByUser(Long userId) {

        return orderRepository.findOrdersByCustomerId(String.valueOf(userId));
    }


    //   PATCH /api/orders/{orderId}/status – Aktualizacja statusu zamówienia
    public void updateOrderStatus(Long orderId) {
        Mono<Boolean> booleanMono = orderRepository.existsById(String.valueOf(orderId.toString()));

    }


}
