package com.shop.purchaseservice.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CartService {

    HashMap<Long, Integer> cart = new HashMap<>();

    public void addToCart(Long id, Integer quantity) {
        cart.put(id, quantity);
    }

    public void removeFromCart(Long id) {
        cart.remove(id);
    }

    public void setQuantityByProductId(Long id, Integer quantity) {
        cart.put(id, quantity);
    }

    public void cleanCart() {
        cart.clear();
    }

    public HashMap<Long, Integer> findAllCart() {
        return cart;
    }


}
