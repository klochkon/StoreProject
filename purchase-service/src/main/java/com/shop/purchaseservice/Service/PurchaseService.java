package com.shop.purchaseservice.Service;

import com.shop.purchaseservice.Client.CustomerClient;
import com.shop.purchaseservice.Client.StorageClient;
import com.shop.purchaseservice.DTO.*;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
    private final KafkaTemplate<String, OrderDuplicateDTO> kafkaAddOrder;
    private final KafkaTemplate<String, MailDTO> kafkaMail;
    private final CustomerClient customerClient;

    @Transactional
    public InventoryStatusDTO purchase(OrderDuplicateDTO orderDuplicateDTO) {
        InventoryStatusDTO inventoryStatusDTO = new InventoryStatusDTO();
        if (storageClient.isOrderInStorage(orderDuplicateDTO.getCart())) {
            purchaseProcess(orderDuplicateDTO);
            inventoryStatusDTO.setIsOrderInStorage(true);

        } else {
            Map<ProductDuplicateDTO, Integer> outOfStorage = storageClient.findOutOfStorageProduct(
                    orderDuplicateDTO.getCart(), orderDuplicateDTO.getCustomerId());
            inventoryStatusDTO.setOutOfStorageProducts(outOfStorage);
        }
        return inventoryStatusDTO;
    }

    public void purchaseProcess(OrderDuplicateDTO orderDuplicateDTO) {
        kafkaAddOrder.send("order-topic", orderDuplicateDTO);

        customerClient.cleanCart(orderDuplicateDTO.getCustomerId());

        Long customerId = orderDuplicateDTO.getCustomerId();
        CustomerDTO customerDTO = customerClient.findCustomerEmailAndNameById(customerId);
        List<String> listOfProducts = new ArrayList<>();

        Map<String, Object> data = Map.of(
                "Cost", orderDuplicateDTO.getCost(),
                "ID", orderDuplicateDTO.getId(),
                "Products", listOfProducts,
                "Name", customerDTO.getName()
        );

        Map<ProductDuplicateDTO, Integer> cart = orderDuplicateDTO.getCart();

        for (Map.Entry<ProductDuplicateDTO, Integer> entry : cart.entrySet()) {
            listOfProducts.add(entry.getKey().getName());
        }

        MailDTO mailDTO;
        mailDTO = MailDTO.builder()
                .to(customerDTO.getEmail())
                .data(data)
                .build();

        kafkaMail.send("mail-topic", mailDTO);
    }
}


