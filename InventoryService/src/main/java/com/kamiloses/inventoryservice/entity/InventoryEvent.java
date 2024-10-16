package com.kamiloses.inventoryservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class InventoryEvent {

    @Id
    private String id;
    @DBRef
    private Inventory inventory;

    private String eventTime;
    private EventType eventType;
    private Integer quantity;
    private String description;

    @DBRef
    private Supplier supplier;

}