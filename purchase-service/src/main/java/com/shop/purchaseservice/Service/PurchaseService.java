package com.shop.purchaseservice.Service;

import com.shop.customerservice.Model.Order;
import com.shop.purchaseservice.Client.StorageClient;
import com.shop.purchaseservice.DTO.InventoryStatusDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final KafkaTemplate<String, Order> kafka;

    public InventoryStatusDTO purchase(Order order) {
        InventoryStatusDTO dto = new InventoryStatusDTO();
        if (storageClient.isOrderInStorage(order.getCart())) {
            kafka.send("order-topic", order);
            dto.setIsOrderInStorage(true);
            return dto;
        } else {
            Map<String, Integer> outOfStorage = storageClient.findOutOfStorageProduct(order.getCart());
            for (Map.Entry<String, Integer> entry : outOfStorage.entrySet()) {
                Map<String, Integer> outOfStorageMap = new HashMap<>();
                outOfStorageMap.put(entry.getKey(), entry.getValue());
                dto.setOutOfStorageProducts(outOfStorageMap);
            }
        }
        return dto;
    }
}


