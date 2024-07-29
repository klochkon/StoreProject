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


    public Boolean isOrderInStorage(@RequestBody HashMap<Long, Integer> cart) {
        return storageClient.isOrderInStorage(cart);
    }

    public void purchase(HashMap<Long, Integer> cart) {
        if(isOrderInStorage(cart)) {

        }

    }





}



