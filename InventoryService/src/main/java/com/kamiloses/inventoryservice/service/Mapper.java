package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.dto.ProductDto;
import com.kamiloses.inventoryservice.entity.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class Mapper {

    protected ProductDto productEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
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


}
