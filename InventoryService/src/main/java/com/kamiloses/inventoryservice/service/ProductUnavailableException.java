package com.kamiloses.inventoryservice.service;

public class ProductUnavailableException extends RuntimeException

{
    public ProductUnavailableException(String message) {
        super(message);
    }
}
