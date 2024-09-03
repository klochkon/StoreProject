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

    @Cacheable(value = "storage", key = "#name")
    public Boolean isInStorage(String name, Integer requiredQuantity) {
        Storage product = repository.findByName(name);
        return product.getQuantity() >= requiredQuantity;
    }

    @CachePut(value = "storage", key = "#addedId")
    public void addProductById(Long addedId, Integer quantityAdded) {
        repository.addProductById(addedId, quantityAdded);
    }

    @CacheEvict(value = "storage", key = "#deletedId")
    public void deleteProductById(Long deletedId, Integer quantityDeleted) {
        repository.deleteProductById(deletedId, quantityDeleted);
    }

    public Boolean isOrderInStorage(Map<String, Integer> cart) {
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            if (!this.isInStorage(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Cacheable(value = "storage", key = "#id")
    public Map<String, Integer> findOutOfStorageProduct(Map<String, Integer> cart) {
        Map<String, Integer> outOfStorageProduct = new HashMap<>();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            if (!isInStorage(entry.getKey(), entry.getValue())) {
                Storage product = repository.findByName(entry.getKey());
                outOfStorageProduct.put(product.getName(), product.getQuantity());
            }
        }
        return outOfStorageProduct;
    }

}