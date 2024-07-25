package com.shop.purchaseservice.Service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PurchaseService {

    HashMap<Long, Integer> cart = new HashMap<>();

    public void addToCart(Long productId, Integer quantity) {
        cart.put(productId, quantity);
    }

    public void removeFromCart(Long productId) {
        cart.remove(productId);
    }

    public void setQuantityByProductId(Long productId, Integer quantity) {
        cart.put(productId, quantity);
    }

    public void cleanCart() {
        cart.clear();
    }

    public HashMap<Long, Integer> findAllCart() {return cart;}

//Purchase todo -------------------------------------------------------------

//--------------------------------------------------------------


}
