package com.shop.purchaseservice.Client;

import com.shop.purchaseservice.DTO.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "customer-service", url = "${url.customerClient}")
public interface CustomerClient {

    @GetMapping("api/v1/customer/find/customerDTO/{customerId}")
    CustomerDTO findCustomerEmailAndNameById(@PathVariable Long customerId);

    @PutMapping("api/v1/customer/clean/cart/{id}")
    void cleanCart(@PathVariable Long id);


}
