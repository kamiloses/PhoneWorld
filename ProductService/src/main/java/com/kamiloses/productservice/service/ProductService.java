package com.kamiloses.productservice.service;

import com.kamiloses.productservice.dto.ProductDto;
import com.kamiloses.productservice.dto.ResponseProductInfo;
import com.kamiloses.productservice.exception.ProductNotFoundException;
import com.kamiloses.productservice.rabbit.RabbitMQProducer;
import com.kamiloses.productservice.repository.ProductRepository;
import jakarta.ws.rs.InternalServerErrorException;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    private Double totalProductsPrice=0.0;
    private Double accountBalance=100000.00;

    public ProductService(ProductRepository productRepository, Mapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll().map(mapper::productEntityToDto);
    }
    public Mono<List<ResponseProductInfo>> getProductPrice(List<ResponseProductInfo> responseProductInfoList) {
      // accountBalance= responseProductInfoList.get(0).getUserAccountBalance();
        return Flux.fromIterable(responseProductInfoList)
                .flatMap(productInfo ->
                        productRepository.getProductsByName(productInfo.getProductName())
                                .flatMap(phone -> {
                                    if (phone == null) {
                                        return Mono.error(new ProductNotFoundException("Product not found: " + productInfo.getProductName()));
                                    }
                                    ResponseProductInfo responseProductInfo = new ResponseProductInfo();
                                    responseProductInfo.setProductName(productInfo.getProductName());
                                    responseProductInfo.setQuantity(productInfo.getQuantity());
                                    responseProductInfo.setPricePerUnit(phone.getPrice());
                                   totalProductsPrice+=phone.getPrice()*productInfo.getQuantity();
//                                    if (totalProductsPrice>responseProductInfo.getUserAccountBalance()) {
//                                        throw new InternalServerErrorException("popraw to potem");//todo zamień
//                                    }
                                            accountBalance-=totalProductsPrice;
                                            totalProductsPrice=0.0;
                                    return Mono.just(responseProductInfo);
                                }

                                )
                )
                .collectList()
                .doFinally(signalType ->{
                    System.out.println("Łączna kwota wydana "+ totalProductsPrice+ " oraz ile posiadam :"+accountBalance);
                    });


    }





//    public Mono<ProductDto> getProductByName(String name) {
//        return productRepository.getProductsByName(name)
//                .map(mapper::productEntityToDto)
//                .switchIfEmpty(Mono.error(new ProductNotFoundException("Product not found with name: " + name)));
//    }

}