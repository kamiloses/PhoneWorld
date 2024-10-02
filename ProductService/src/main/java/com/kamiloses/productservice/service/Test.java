package com.kamiloses.productservice.service;//package com.kamiloses.productservice.service;
//
//import com.kamiloses.productservice.entity.Product;
//import com.kamiloses.productservice.repository.ProductRepository;
//import jakarta.annotation.PostConstruct;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Test {
//
//    private ProductRepository productRepository;
//
//    public Test(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @PostConstruct
//    public void saveEntities() {
//        Product product = new Product();
//        product.setId("4");
//        product.setName("Smartphone Z");
//        product.setManufacturer("TechCorp");
//        product.setModel("X123");
//        product.setOperatingSystem("Android 11");
//        product.setScreenSize(6.5);
//        product.setBatteryCapacity(4000);
//        product.setCameraResolution(48);
//        product.setRam(8);
//        product.setStorageCapacity(128);
//        product.setPrice(699.99);
//
//        // Zapisanie produktu
//        productRepository.save(product).block();
//    }
//}