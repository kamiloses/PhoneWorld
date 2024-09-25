package com.kamiloses.orderservice.repository;

import com.kamiloses.orderservice.entity.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}
