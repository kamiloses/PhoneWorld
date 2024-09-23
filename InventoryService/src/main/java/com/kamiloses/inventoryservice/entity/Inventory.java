package com.kamiloses.inventoryservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Inventory {
    @Id
    private String id;

   @DBRef
    private Product product;

    private Integer availableQuantity;
    private Integer reservedQuantity;
    private Integer soldQuantity;

  //  @OneToMany(mappedBy = "inventory")
//    @Transient
//    private List<InventoryEvent> events;
}