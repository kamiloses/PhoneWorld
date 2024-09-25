package com.kamiloses.orderservice.service;

import com.kamiloses.orderservice.dto.MakeAnOrderDto;
import com.kamiloses.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
private final OrderRepository orderRepository;
private final Mapper mapper;

    public OrderService(OrderRepository orderRepository, Mapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }
 //   GGET /api/orders/customer/{customerId} – Pobranie zamówień klienta
 //   PUT /api/orders/{orderId}/cancel – Anulowanie zamówienia uzywam thread
 //   PATCH /api/orders/{orderId}/status – Aktualizacja statusu zamówienia
 //   GET /api/orders pobieranie moich zamówień


    public Mono<Void> makeAnOrder(MakeAnOrderDto makeAnOrderDto) {
    return orderRepository.save(mapper.makeAnOrderDtoToOrder(makeAnOrderDto)).then();

    }






}
