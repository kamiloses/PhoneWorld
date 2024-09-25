package com.kamiloses.orderservice.service;

import com.kamiloses.orderservice.dto.MakeAnOrderDto;
import com.kamiloses.orderservice.dto.OrderItemDto;
import com.kamiloses.orderservice.entity.Order;
import com.kamiloses.orderservice.entity.OrderItem;
import com.kamiloses.orderservice.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class Mapper {


    Order makeAnOrderDtoToOrder(MakeAnOrderDto orderDto) {
        Order order = new Order();
        order.setCustomerId(null);
        order.setOrderItems(orderItemDtoToEntity(orderDto.getOrderItems()));
        order.setStatus(OrderStatus.PENDING);

return order;    }

    List<OrderItem> orderItemDtoToEntity(List<OrderItemDto> orderItemDto) {
    return     orderItemDto.stream().map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductName(item.getProductName());
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setPrice(100.0);
                    return orderItem;
                }).toList();
    }


}
