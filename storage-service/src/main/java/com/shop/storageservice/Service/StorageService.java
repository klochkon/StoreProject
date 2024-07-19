package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository repository;

    public Boolean IsInStorage(Storage product, Integer requiredquentity) {

        if(product.getQuantity() < requiredquentity) {return false;}

        else {return true;}

    }

}
