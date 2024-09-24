package com.kamiloses.inventoryservice.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Supplier {

    //todo dodaj role supplier
    @Id
    private String accountId;
    private String firstName;
    private String lastName;

    //@OneToMany(mappedBy = "supplier")
    //private List<Product> products;
}