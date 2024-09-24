package com.kamiloses.productservice.handler;

import com.kamiloses.productservice.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProductHandler {

private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

public Mono<ServerResponse> getAllPhones(ServerRequest request) {
        return productService.getAllProducts().collectList().flatMap(product->
                ServerResponse.ok().bodyValue(product));

}

}
