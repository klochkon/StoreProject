package com.shop.purchaseservice.Service;

import com.shop.purchaseservice.Client.CustomerClient;
import com.shop.purchaseservice.Client.StorageClient;
import com.shop.purchaseservice.DTO.InventoryStatusDTO;
import com.shop.purchaseservice.DTO.MailDTO;
import com.shop.purchaseservice.DTO.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final KafkaTemplate<String, OrderDTO> kafkaAddOrder;
    private final KafkaTemplate<String, MailDTO> kafkaMail;
    private final CustomerClient customerClient;

    public InventoryStatusDTO purchase(OrderDTO orderDTO) {
        InventoryStatusDTO dto = new InventoryStatusDTO();
        if (storageClient.isOrderInStorage(orderDTO.getCart())) {

            kafkaAddOrder.send("order-topic", orderDTO);

            Long customerId = orderDTO.getCustomerId();
            String email = customerClient.findCustomerEmailById(customerId);

            MailDTO mailDTO = new MailDTO();
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("Cost", orderDTO.getCost());
            List<String> listOfProducts = new List<String>();
            data.put();

            mailDTO.setEmail(email);
            mailDTO.setData(data);

            kafkaMail.send("purchase-mail-topic", mailDTO);
            dto.setIsOrderInStorage(true);
            return dto;
        } else {
            Map<String, Integer> outOfStorage = storageClient.findOutOfStorageProduct(orderDTO.getCart());
            for (Map.Entry<String, Integer> entry : outOfStorage.entrySet()) {
                Map<String, Integer> outOfStorageMap = new HashMap<>();
                outOfStorageMap.put(entry.getKey(), entry.getValue());
                dto.setOutOfStorageProducts(outOfStorageMap);
            }
        }
        return dto;
    }
}


