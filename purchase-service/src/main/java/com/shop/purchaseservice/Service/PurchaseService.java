package com.shop.purchaseservice.Service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PurchaseService {

    HashMap<String, Integer> cart = new HashMap<>();

    public void addToCart(String name, Integer quantity) {cart.put(name, quantity);}

    public void removeFromCart(String name) {cart.remove(name);}

    public void setQuantity(String name, Integer quantity) {cart.put(name, quantity);}

    public void cleanCart() {cart.clear();}

//Purchase todo -------------------------------------------------------------

//--------------------------------------------------------------


}
