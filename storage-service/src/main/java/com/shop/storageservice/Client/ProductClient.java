package com.shop.storageservice.Client;

import com.shop.storageservice.DTO.ProductWithQuantityDTO;
import com.shop.storageservice.DTO.StorageDuplicateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service", url = "${url.productClient}")
public interface ProductClient {

    @GetMapping("api/v1/product/get/all")
    List<ProductWithQuantityDTO> getAllProductWithQuantity(List<StorageDuplicateDTO> storageList);
}
