package com.kamiloses.orderservice.router;

import com.kamiloses.orderservice.handler.OrderHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class OrderRouter {

    private final OrderHandler orderHandler;

    public OrderRouter(OrderHandler orderHandler) {
        this.orderHandler = orderHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(OrderHandler orderHandler) {
        return RouterFunctions.route().POST("/api/createAccount", orderHandler::makeAnOrder).build();
    }
}