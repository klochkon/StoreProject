package com.shop.purchaseservice.Controller;


import com.shop.purchaseservice.DTO.InventoryStatusDTO;
import com.shop.purchaseservice.DTO.OrderDTO;
import com.shop.purchaseservice.Service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/purchase")
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping("operation")
    public InventoryStatusDTO purchase(@RequestBody OrderDTO orderDTO) {
        return service.purchase(orderDTO);
    }
}
