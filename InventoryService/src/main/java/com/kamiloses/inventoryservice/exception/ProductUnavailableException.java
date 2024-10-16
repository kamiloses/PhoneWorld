package com.kamiloses.inventoryservice.exception;

public class ProductUnavailableException extends RuntimeException

{
    public ProductUnavailableException(String message) {
        super(message);
    }
}
