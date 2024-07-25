package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository repository;

    @Cacheable(value = "storage", key = "#id")
    public Storage findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Boolean isInStorage(Long id, Integer requiredQuantity) {
        Storage product = repository.findById(id).orElse(null);
        return product.getQuantity() >= requiredQuantity;

    }
    @CachePut(value = "storage", key = "#addedId")
    public void addProductById(Long addedId, Integer quantityAdded) {
        repository.addProductById(addedId, quantityAdded);
    }

    @CacheEvict(value = "storage", key = "#deletedId")
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

}