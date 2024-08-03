package com.shop.purchaseservice.Service;

import com.shop.customerservice.Model.Order;
import com.shop.purchaseservice.Client.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final KafkaTemplate<String, Order> kafka;

    public String purchase(Order order) {
        Map<String, Integer> outOfStorage = storageClient.findOutOfStorageProduct(order.getCart());
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Order wasn`t reserved, because there are only ");
        if (storageClient.isOrderInStorage(order.getCart())) {
            kafka.send("order-topic", order);
            return "Order was reserved!";
        } else {
            for (Map.Entry<String, Integer> entry : outOfStorage.entrySet()) {
                messageBuilder.append(entry.getValue())
                        .append(" of ")
                        .append(entry.getKey())
                        .append(", ");
            }
            messageBuilder.setLength(messageBuilder.length() - 2);
        }
        return messageBuilder.toString();
    }
}


