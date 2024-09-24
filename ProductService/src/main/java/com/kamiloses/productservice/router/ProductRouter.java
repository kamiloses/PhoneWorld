package com.kamiloses.productservice.router;

import com.kamiloses.productservice.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class ProductRouter {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(ProductHandler productHandler) {
        return RouterFunctions.route().GET("/api/products",productHandler::getAllPhones)
                .build();

    }

}
