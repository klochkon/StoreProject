package com.shop.purchaseservice.Controller;


import com.shop.purchaseservice.Service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping("add-to-cart")
    public void addToCart(@RequestParam Long id, Integer quantity) {
        service.addToCart(id, quantity);
    }

    @DeleteMapping("remove-{}")
    public void removeFromCart(@PathVariable Long id) {
        service.removeFromCart(id);
    }

    @PutMapping("set-quantity")
    public void setQuantity(@RequestParam Long productId, Integer quantity) {
        service.setQuantityByProductId(productId, quantity);
    }

    @DeleteMapping("clean-cart")
    public void cleanCart() {
        service.cleanCart();
    }

    @GetMapping("find-all-cart")
    public HashMap<Long, Integer> findAllCart() {return service.findAllCart();}


}
