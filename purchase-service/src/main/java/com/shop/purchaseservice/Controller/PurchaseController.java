package com.shop.purchaseservice.Controller;


import com.shop.customerservice.Model.Order;
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
    public String purchase(@RequestBody Order order) {
        return service.purchase(order);
    }
}
