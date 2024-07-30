package com.shop.purchaseservice.Client;

import com.shop.customerservice.Model.Order;
import jakarta.transaction.Transactional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "customer-service", url = "http://localhost:8081")
public interface CustomerClient {

    @PostMapping("api/v1/order/save")
    void saveOrder(@RequestBody Order order);


}
