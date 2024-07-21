package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository repository;

    public Storage findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public Boolean isInStorage(Long id, Integer requiredquentity) {
        Storage product = findById(id);
        return product.getQuantity() >= requiredquentity;

    }

    public void addProductById(Long addedId, Integer quantityAdded) {
        repository.addProductById(addedId, quantityAdded);
    }

    public void deleteProductById(Long deletedId, Integer quantityDeleted) {
        repository.deleteproductById(deletedId, quantityDeleted);
    }

    public Object isOrderInStorage(HashMap<Long, Integer> cart) {
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Long id = entry.getKey();
            Integer quantity = entry.getValue();
            if (!isInStorage(id, quantity)) {
                return id;
            }
        }
        return true;
    }

    public void addProductByIdByOne(Long addedId) {
        repository.addProductByIdByOne(addedId);
    }

    public void deleteProductByIdByOne(Long deletedId) {
        repository.deleteProductByIdByOne(deletedId);
    }
}