package com.shop.purchaseservice.Controller;


import com.shop.purchaseservice.Service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping("add-to-cart")
    public void addToCart(@RequestParam String name, Integer quantity) {service.addToCart(name, quantity);}

    @DeleteMapping("remove-{}")
    public void removeFromCart(@PathVariable String name) {service.removeFromCart(name);}

    @PutMapping("set-quantity")
    public void setQuantity(@RequestParam String name, Integer quantity) {service.setQuantity(name, quantity);}

    @DeleteMapping("clean-cart")
    public void cleanCart() {service.cleanCart();}


}
