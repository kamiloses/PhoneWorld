package com.kamiloses.orderservice.handler;

import com.kamiloses.orderservice.dto.MakeAnOrderDto;
import com.kamiloses.orderservice.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }


    public Mono<ServerResponse> makeAnOrder(ServerRequest request) {
        return request.bodyToMono(MakeAnOrderDto.class)
                .flatMap(orderService::makeAnOrder)
                .then(ServerResponse.ok().bodyValue("Order has been made"))
                .onErrorResume(error -> ServerResponse.badRequest().bodyValue("Failed to make order: " + error.getMessage()));
    }

}
