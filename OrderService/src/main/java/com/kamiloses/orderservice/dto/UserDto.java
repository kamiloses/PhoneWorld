package com.kamiloses.orderservice.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {


    private String email;
    private String password;
    private String role;
    private Double accountBalance;
    private List<Long> ordersId;




}
