package com.shop.purchaseservice.Service;

import com.shop.purchaseservice.Client.CustomerClient;
import com.shop.purchaseservice.Client.StorageClient;
import com.shop.purchaseservice.DTO.CustomerDTO;
import com.shop.purchaseservice.DTO.InventoryStatusDTO;
import com.shop.purchaseservice.DTO.MailDTO;
import com.shop.purchaseservice.DTO.OrderDublicateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final KafkaTemplate<String, OrderDublicateDTO> kafkaAddOrder;
    private final KafkaTemplate<String, MailDTO> kafkaMail;
    private final CustomerClient customerClient;

    public InventoryStatusDTO purchase(OrderDublicateDTO orderDublicateDTO) {
        InventoryStatusDTO dto = new InventoryStatusDTO();
        if (storageClient.isOrderInStorage(orderDublicateDTO.getCart())) {

            kafkaAddOrder.send("order-topic", orderDublicateDTO);

            Long customerId = orderDublicateDTO.getCustomerId();
            CustomerDTO customerDTO = customerClient.findCustomerEmailAndNameById(customerId);
            List<String> listOfProducts = new ArrayList<String>();

            Map<String, Object> data = Map.of(
                    "Cost", orderDublicateDTO.getCost(),
                    "ID", orderDublicateDTO.getId(),
                    "Products", listOfProducts,
                    "Name", customerDTO.getName()
            );

            Map<String, Integer> cart = orderDublicateDTO.getCart();

            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                listOfProducts.add(entry.getKey());
            }

            MailDTO mailDTO;
            mailDTO = MailDTO.builder()
                    .to(customerDTO.getEmail())
                    .data(data)
                    .build();

            kafkaMail.send("purchase-mail-topic", mailDTO);
            dto.setIsOrderInStorage(true);
        } else {
            Map<String, Integer> outOfStorage = storageClient.findOutOfStorageProduct(orderDublicateDTO.getCart());
            for (Map.Entry<String, Integer> entry : outOfStorage.entrySet()) {
                Map<String, Integer> outOfStorageMap = new HashMap<>();
                outOfStorageMap.put(entry.getKey(), entry.getValue());
                dto.setOutOfStorageProducts(outOfStorageMap);
            }
        }
        return dto;
    }
}


