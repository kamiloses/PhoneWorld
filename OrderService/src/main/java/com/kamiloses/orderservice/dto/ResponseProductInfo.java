package com.kamiloses.orderservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseProductInfo {
    private String productId;
    private String productName;
    private Integer quantity;
    private Double pricePerUnit;
    private Double userAccountBalance;


}
