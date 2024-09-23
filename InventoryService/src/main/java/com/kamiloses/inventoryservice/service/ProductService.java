package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.dto.ProductDto;
import com.kamiloses.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

private final ProductRepository productRepository;
private final Mapper mapper;
    public ProductService(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Flux<ProductDto> getAllProducts(){
    return productRepository.findAll().map(mapper::productEntityToDto);

}

    public Mono<ProductDto> getProductByName(String name) {
        return productRepository.getProductsByName(name)
                .map(mapper::productEntityToDto)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found with name: " + name)));
    }


}
