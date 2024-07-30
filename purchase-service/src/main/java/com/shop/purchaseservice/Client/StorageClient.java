package com.shop.purchaseservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;


@FeignClient(name = "storage-service", url = "http://localhost:8084")
public interface StorageClient {

    @PostMapping("api/v1/storage/order-check")
    Boolean isOrderInStorage(@RequestBody HashMap<Long, Integer> cart);

    @GetMapping("api/v1/storage/find/order/out")
    HashMap<String, Integer> findOutOfStorageProduct(@RequestBody HashMap<Long, Integer> cart);




}
