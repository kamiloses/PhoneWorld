package com.kamiloses.userservice;

import jakarta.ws.rs.GET;
import lombok.*;

import java.io.Serializable;
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
