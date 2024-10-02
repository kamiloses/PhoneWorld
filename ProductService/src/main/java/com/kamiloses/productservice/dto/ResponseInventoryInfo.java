package com.kamiloses.productservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseInventoryInfo {

    private String productId;
    private String productName;
    private Integer productQuantity;


}
