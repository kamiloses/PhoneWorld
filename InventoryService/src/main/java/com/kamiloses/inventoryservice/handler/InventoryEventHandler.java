package com.kamiloses.inventoryservice.handler;

import com.kamiloses.inventoryservice.service.InventoryEventDto;
import com.kamiloses.inventoryservice.service.InventoryEventService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class InventoryEventHandler {

private final InventoryEventService inventoryEventService;

    public InventoryEventHandler(InventoryEventService inventoryEventService) {
        this.inventoryEventService = inventoryEventService;
    }

public Mono<ServerResponse> deliverPhonesToInventory(ServerRequest serverRequest) {
return serverRequest.bodyToMono(InventoryEventDto.class).flatMap(inventoryEventService::addPhoneToWereHouse)
        .then(ServerResponse.ok().bodyValue("The phones has been delivered successfully"));




}



}
