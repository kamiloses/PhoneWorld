package com.kamiloses.inventoryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullOrderDetailsDto {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;
    private Double userAccountBalance;


}
