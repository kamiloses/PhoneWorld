package com.kamiloses.orderservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Order {
    @Id
    private String id;

    private String customerId;
    @Field("items")
    private List<OrderItem> orderItems;

    private OrderStatus status;

    private LocalDateTime createdAt; //data złozenia zamówienia

    private LocalDateTime updatedAt; // aktualizacja statusu zamówienia
}