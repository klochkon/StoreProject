package com.shop.purchaseservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;


@FeignClient(name = "storage-service", url = "${url.storageClient}")
public interface StorageClient {

    @PostMapping("api/v1/storage/order-check")
    Boolean isOrderInStorage(@RequestBody Map<String, Integer> cart);

    @GetMapping("api/v1/storage/find/order/out")
    Map<String, Integer> findOutOfStorageProduct(@RequestBody Map<String, Integer> cart);
}
