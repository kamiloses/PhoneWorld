package com.kamiloses.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
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

    //  @Transient// @OneToOne(mappedBy)
    //private Inventory inventory;
}