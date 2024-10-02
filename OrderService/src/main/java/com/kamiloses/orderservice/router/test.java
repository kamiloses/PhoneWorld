package com.kamiloses.orderservice.router;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

@GetMapping
public String sayHello() {
    return "Hello World";
}
}
