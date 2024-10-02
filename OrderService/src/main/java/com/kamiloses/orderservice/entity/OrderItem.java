package com.kamiloses.orderservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderItem {
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;


}
