package com.kamiloses.productservice.service;

import com.kamiloses.productservice.dto.ProductDto;
import com.kamiloses.productservice.dto.ResponseInventoryInfo;
import com.kamiloses.productservice.dto.FullOrderDetailsDto;
import com.kamiloses.productservice.entity.Product;
import com.kamiloses.productservice.repository.ProductRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
public class Mapper {

    private final ProductRepository productRepository;

    public Mapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected ProductDto productEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setManufacturer(product.getManufacturer());
        productDto.setModel(product.getModel());
        productDto.setOperatingSystem(product.getOperatingSystem());
        productDto.setScreenSize(product.getScreenSize());
        productDto.setBatteryCapacity(product.getBatteryCapacity());
        productDto.setCameraResolution(product.getCameraResolution());
        productDto.setRam(product.getRam());
        productDto.setStorageCapacity(product.getStorageCapacity());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
    public Flux<ResponseInventoryInfo> productInfoToInventoryInfo(List<FullOrderDetailsDto> fullOrderDetailsDtoList) {
        return Flux.fromIterable(fullOrderDetailsDtoList)
                .flatMap(productInfo ->
                        productRepository.getProductsByName(productInfo.getProductName())
                                .map(product -> {
                                    ResponseInventoryInfo responseInventoryInfo = new ResponseInventoryInfo();
                                    responseInventoryInfo.setProductId(product.getId());
                                    responseInventoryInfo.setProductName(productInfo.getProductName());
                                    responseInventoryInfo.setProductQuantity(productInfo.getQuantity());
                                    return responseInventoryInfo;
                                })
                );
    }



}