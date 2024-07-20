package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository repository;

    public Boolean isInStorage(Storage product, Integer requiredquentity) {

        if (product.getQuantity() < requiredquentity) {
            return false;
        } else {
            return true;
        }
    }

    public void addProductById(Long addedId, Integer quantityAdded) {
        repository.addProductById(addedId, quantityAdded);
    }

    public void deleteProductById(Long deletedId, Integer quantityDeleted) {
            repository.deleteproductById(deletedId, quantityDeleted);
    }
}