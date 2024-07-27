package com.shop.purchaseservice.Service;

import com.shop.purchaseservice.Client.StorageClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final StorageClient storageClient;
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

    public HashMap<Long, Integer> findAllCart() {
        return cart;
    }

    public Object isOrderInStorage(@RequestBody HashMap<Long, Integer> cart) {
        return storageClient.isOrderInStorage(cart);
    }





}



