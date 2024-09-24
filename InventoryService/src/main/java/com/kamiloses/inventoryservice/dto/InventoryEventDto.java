package com.kamiloses.inventoryservice.dto;

import com.kamiloses.inventoryservice.entity.Supplier;
import lombok.Data;
import lombok.NoArgsConstructor;

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
