package com.shop.purchaseservice.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${url.customerClient}")
public interface CustomerClient {

    String findCustomerEmailById(@PathVariable Long customerId);

}
