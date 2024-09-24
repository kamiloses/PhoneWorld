package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InventoryEventDto {

    private String inventoryName;
    private LocalDateTime eventTime;
    private Integer quantityChange;
    private String description;

    private Supplier supplier;


}
