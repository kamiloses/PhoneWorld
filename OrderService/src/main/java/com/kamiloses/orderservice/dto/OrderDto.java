package com.kamiloses.orderservice.dto;

import com.kamiloses.orderservice.entity.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {


    private List<OrderItemDto> orderItems;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
