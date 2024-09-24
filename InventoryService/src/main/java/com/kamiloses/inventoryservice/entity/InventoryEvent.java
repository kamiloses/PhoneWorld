package com.kamiloses.inventoryservice.entity;

import com.kamiloses.inventoryservice.EventType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import reactor.core.publisher.Mono;

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
    private String eventTime;
    private EventType eventType;
    private Integer quantity;
    private String description;

    @DBRef
    private Supplier supplier;

}