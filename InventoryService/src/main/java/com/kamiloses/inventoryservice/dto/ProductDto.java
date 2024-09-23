package com.kamiloses.inventoryservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

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
