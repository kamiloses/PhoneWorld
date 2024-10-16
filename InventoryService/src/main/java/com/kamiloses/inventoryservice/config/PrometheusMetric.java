package com.kamiloses.inventoryservice.config;

import com.kamiloses.inventoryservice.entity.Inventory;
import com.kamiloses.inventoryservice.repository.InventoryRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class PrometheusMetric {


private final InventoryRepository inventoryRepository;

    public PrometheusMetric(MeterRegistry meterRegistry, InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;

        Gauge.builder("Iphone14_current_Availability", this::iphone14Amount)
                .register(meterRegistry);

        Gauge.builder("Iphone13Pro_current_Availability", this::iphone13ProAmount)
                .register(meterRegistry);

        Gauge.builder("Iphone12_current_Availability", this::iphone12Amount)
                .register(meterRegistry);

        Gauge.builder("Iphone11_current_Availability", this::iphone11Amount)
                .register(meterRegistry);

        Gauge.builder("IphoneSE_current_Availability", this::iphoneSEAmount)
                .register(meterRegistry);
    }


public Integer iphone14Amount() {
       return inventoryRepository.findByProductId("1")
               .map(Inventory::getAvailableQuantity).block();

}
    public Integer iphone13ProAmount() {
        return inventoryRepository.findByProductId("2")
                .map(Inventory::getAvailableQuantity).block();

    }public Integer iphone12Amount() {
        return inventoryRepository.findByProductId("3")
                .map(Inventory::getAvailableQuantity).block();

    }public Integer iphone11Amount() {
        return inventoryRepository.findByProductId("4")
                .map(Inventory::getAvailableQuantity).block();

    }public Integer iphoneSEAmount() {
        return inventoryRepository.findByProductId("5")
                .map(Inventory::getAvailableQuantity).block();

    }


}