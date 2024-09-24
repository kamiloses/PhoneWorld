package com.kamiloses.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private String manufacturer;
    private String model;
    private String operatingSystem;
    private Double screenSize;
    private Integer batteryCapacity;
    private Integer cameraResolution;
    private Integer ram;
    private Integer storageCapacity;
    private Double price;

}
