package com.kamiloses.inventoryservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document
@Data
@NoArgsConstructor
public class InventoryEvent {

    @Id
    private String id;
    @DBRef
    private Inventory inventory;
       // todo uzyj potem convertera by to była w stringu data
    private LocalDateTime eventTime;
    private String eventType;
    private Integer quantityChange;
    private String description;

}