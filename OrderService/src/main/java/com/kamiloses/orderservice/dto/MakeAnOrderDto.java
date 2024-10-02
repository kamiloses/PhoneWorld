package com.kamiloses.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MakeAnOrderDto {

    private List<OrderItemDto> orderItems;


}
