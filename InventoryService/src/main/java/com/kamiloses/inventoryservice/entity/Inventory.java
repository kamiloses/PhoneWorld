package com.kamiloses.inventoryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private String id;

//    @DBRef
    private String productId;

    private Integer availableQuantity;
    private Integer soldQuantity;

  //  @OneToMany(mappedBy = "inventory")
//    @Transient
//    private List<InventoryEvent> events;
}