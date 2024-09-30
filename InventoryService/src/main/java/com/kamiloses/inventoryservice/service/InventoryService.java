package com.kamiloses.inventoryservice.service;

import com.kamiloses.inventoryservice.dto.ResponseInventoryInfo;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Mono<Void> responseIfProductAvailable(List<ResponseInventoryInfo> responseInventoryInfoList) {
        for (ResponseInventoryInfo responseInventoryInfo : responseInventoryInfoList) {
            System.out.println("response"+responseInventoryInfo);
        }
        return Flux.fromIterable(responseInventoryInfoList)
                .flatMap(product -> inventoryRepository.findByProductId(product.getProductId())
                        .flatMap(inventory -> {
                            if (product.getProductQuantity() > inventory.getAvailableQuantity()) {
                                return Mono.error(new ProductUnavailableException(
                                        "Product: " + product.getProductName() + " is currently not available"));
                            }
                            inventory.setAvailableQuantity(inventory.getAvailableQuantity() - product.getProductQuantity());
                            return Mono.just(inventory);
                        })
                )
                .collectList()
                .flatMap(inventories -> inventoryRepository.saveAll(inventories).then());
    }
}
