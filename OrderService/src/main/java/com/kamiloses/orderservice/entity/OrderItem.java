package com.kamiloses.orderservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@NoArgsConstructor
public class OrderItem {
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;


}
