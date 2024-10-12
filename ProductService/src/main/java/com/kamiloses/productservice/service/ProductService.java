package com.kamiloses.productservice.service;

import com.kamiloses.productservice.dto.ProductDto;
import com.kamiloses.productservice.dto.FullOrderDetailsDto;
import com.kamiloses.productservice.exception.ProductNotFoundException;
import com.kamiloses.productservice.rabbit.RabbitMQProducer;
import com.kamiloses.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;
    private final RabbitMQProducer rabbitMQProducer;

    private Double totalProductsPrice = 0.0;
    private Double accountBalance = 100000.00;

    public ProductService(ProductRepository productRepository, Mapper mapper, RabbitMQProducer rabbitMQProducer) {
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(mapper::productEntityToDto);
    }

    public Mono<List<FullOrderDetailsDto>> getProductPrice(List<FullOrderDetailsDto> fullOrderDetailsDtoList) {
        // accountBalance= responseProductInfoList.get(0).getUserAccountBalance();
        return Flux.fromIterable(fullOrderDetailsDtoList)
                .flatMap(productInfo ->
                        productRepository.getProductsByName(productInfo.getProductName())
                                .flatMap(phone -> {
                                            if (phone == null) {
                                                return Mono.error(new ProductNotFoundException("Product not found: " + productInfo.getProductName()));
                                            }
                                            FullOrderDetailsDto fullOrderDetailsDto = new FullOrderDetailsDto();
                                            fullOrderDetailsDto.setProductId(phone.getId());
                                            fullOrderDetailsDto.setProductName(productInfo.getProductName());
                                            fullOrderDetailsDto.setQuantity(productInfo.getQuantity());
                                            fullOrderDetailsDto.setPricePerUnit(phone.getPrice());
                                            totalProductsPrice += phone.getPrice() * productInfo.getQuantity();

                                            accountBalance -= totalProductsPrice;
                                            totalProductsPrice = 0.0;



                                            return Mono.just(fullOrderDetailsDto);
                                        }

                                )
                )
                .collectList().doFinally(signalType -> {
                    System.out.println("total product price" + totalProductsPrice + " and my account Balance :" + accountBalance);
                })
                .doOnSuccess(fullOrderDetailsDto->rabbitMQProducer.sendMessageToInventory(fullOrderDetailsDto));


    }


//    public Mono<ProductDto> getProductByName(String name) {
//        return productRepository.getProductsByName(name)
//                .map(mapper::productEntityToDto)
//                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found with name: " + name)));
//    }

}