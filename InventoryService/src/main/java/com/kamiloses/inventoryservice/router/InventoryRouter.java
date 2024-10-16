package com.kamiloses.inventoryservice.router;

import com.kamiloses.inventoryservice.handler.InventoryEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Controller
public class InventoryRouter {


    private final InventoryEventHandler inventoryEventHandler;

    public InventoryRouter(InventoryEventHandler inventoryEventHandler) {
        this.inventoryEventHandler = inventoryEventHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction(InventoryEventHandler inventoryEventHandler) {
        return RouterFunctions.route().POST("/api/makeADeliver", inventoryEventHandler::deliverPhonesToInventory)
                .build();

    }


}
