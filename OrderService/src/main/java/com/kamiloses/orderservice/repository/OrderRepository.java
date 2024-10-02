package com.kamiloses.orderservice.repository;

import com.kamiloses.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
    Flux<Order> findOrdersByCustomerId(String customerId);
    //todo zobacz jak to by wyglądało z Mono
}
