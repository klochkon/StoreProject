package com.shop.purchaseservice.Client;

import com.shop.purchaseservice.DTO.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${url.customerClient}")
public interface CustomerClient {

    @GetMapping("api/v1/customer/find/customerDTO/{customerId}")
    CustomerDTO findCustomerEmailAndNickNameById(@PathVariable Long customerId);

}
