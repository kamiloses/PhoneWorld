package com.kamiloses.orderservice.service;

import com.kamiloses.orderservice.dto.MakeAnOrderDto;
import com.kamiloses.orderservice.dto.ResponseProductInfo;
import com.kamiloses.orderservice.entity.Order;
import com.kamiloses.orderservice.entity.OrderItem;
import com.kamiloses.orderservice.rabbit.RabbitMQConsumer;
import com.kamiloses.orderservice.rabbit.RabbitMQProducer;
import com.kamiloses.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
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
 //   GGET /api/orders/customer/{customerId} – Pobranie zamówień klienta
 //   PUT /api/orders/{orderId}/cancel – Anulowanie zamówienia uzywam thread
 //   PATCH /api/orders/{orderId}/status – Aktualizacja statusu zamówienia
 //   GET /api/orders pobieranie moich zamówień

    public Mono<Void> makeAnOrder(MakeAnOrderDto makeAnOrderDto) {
        List<ResponseProductInfo> requestForProductPrice= mapper.OrderItemDtoToResponseProductInfo(makeAnOrderDto.getOrderItems());
        rabbitMQProducer.sendMessageToProductService(requestForProductPrice);
        List<ResponseProductInfo> responseFromProductService = rabbitMQConsumer.getModifiedResponseFromProductService();
        //request and response about product price

        Order order = mapper.makeAnOrderDtoToOrder(makeAnOrderDto);
        System.err.println("Wynik"+responseFromProductService);
        order.setOrderItems(mapper.responseProductInfoToOrderItem(responseFromProductService));
        System.out.println("Wynik"+order);



        return orderRepository.save(order).then();

    }






}
