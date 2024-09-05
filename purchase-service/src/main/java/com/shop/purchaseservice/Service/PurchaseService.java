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
            CustomerDTO customerDTO = customerClient.findCustomerEmailAndNickNameById(customerId);

            MailDTO mailDTO = new MailDTO();
            Map<String, Object> data = new HashMap<String, Object>();
            Map<String, Integer> cart = orderDublicateDTO.getCart();
            List<String> listOfProducts = new ArrayList<String>();

            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
                listOfProducts.add(entry.getKey());
            }

            data.put("Cost", orderDublicateDTO.getCost());
            data.put("ID", orderDublicateDTO.getId());
            data.put("Products", listOfProducts);
            data.put("Nickname", customerDTO.getNickName());

            mailDTO.setEmail(customerDTO.getEmail());
            mailDTO.setData(data);

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


