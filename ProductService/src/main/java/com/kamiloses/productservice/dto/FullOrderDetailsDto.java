package com.kamiloses.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FullOrderDetailsDto {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;
    private Double userAccountBalance;


}
