package com.kamiloses.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Order {
    @Id
    private String id;

    private String customerId;

    private List<OrderItem> orderItems;

    private OrderStatus status;

    private LocalDateTime createdAt; //data złozenia zamówienia

    private LocalDateTime updatedAt; // aktualizacja statusu zamówienia
}