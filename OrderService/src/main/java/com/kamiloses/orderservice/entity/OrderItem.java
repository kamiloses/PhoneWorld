package com.kamiloses.orderservice.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderItem {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double price;


}
