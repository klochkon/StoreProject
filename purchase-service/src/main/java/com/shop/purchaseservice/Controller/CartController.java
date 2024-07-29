package com.shop.purchaseservice.Controller;


import com.shop.purchaseservice.Service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService service;

    @PostMapping("add")
    public void addToCart(@RequestParam Long id, @RequestParam Integer quantity) {
        service.addToCart(id, quantity);
    }

    @DeleteMapping("remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        service.removeFromCart(id);
    }

    @PutMapping("set/quantity")
    public void setQuantity(@RequestParam Long id, @RequestParam Integer quantity) {
        service.setQuantityByProductId(id, quantity);
    }

    @DeleteMapping("clean-cart")
    public void cleanCart() {
        service.cleanCart();
    }

    @GetMapping("find-all-cart")
    public HashMap<Long, Integer> findAllCart() {
        return service.findAllCart();
    }
}
