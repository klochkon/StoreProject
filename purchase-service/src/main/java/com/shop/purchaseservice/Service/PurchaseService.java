package com.shop.purchaseservice.Service;

import com.shop.customerservice.Model.Order;
import com.shop.purchaseservice.Client.CustomerClient;
import com.shop.purchaseservice.Client.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final CustomerClient customerClient;


    public String purchase(Order order) {
        HashMap<String, Integer> outOfStorage = storageClient.findOutOfStorageProduct(order.getCart());
        String message = "Order wasn`t reserved, because there are only ";
        if (storageClient.isOrderInStorage(order.getCart())) {
            customerClient.saveOrder(order);
            return "Order was reserved!";
        } else {
            for (HashMap.Entry<String, Integer> entry : outOfStorage.entrySet()) {
                message += entry.getValue() + "of" + entry.getKey() + ", ";
            }
            message = message.substring(0, message.length() - 2);
        }
        return message;
    }
}


