package com.kamiloses.productservice.service;

import com.kamiloses.productservice.dto.ProductDto;
import com.kamiloses.productservice.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

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

}