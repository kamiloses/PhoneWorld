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
         rabbitMQProducer.sendMessage(mapper.OrderItemDtoToResponseProductInfo(makeAnOrderDto.getOrderItems()));
        List<ResponseProductInfo> modifiedResponseFromProductService = rabbitMQConsumer.getModifiedResponseFromProductService();
        Order order = mapper.makeAnOrderDtoToOrder(makeAnOrderDto);
        List<OrderItem> modifiedOrderItems = mapper.responseProductInfoToOrderItem(modifiedResponseFromProductService);
        order.setOrderItems(modifiedOrderItems);
        return orderRepository.save(order).then();

    }






}
