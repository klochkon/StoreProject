package com.shop.storageservice.Service;

import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository repository;

    @Cacheable(value = "storage", key = "#id")
    public Storage findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Cacheable(value = "storage", key = "#id")
    public Boolean isInStorage(Long id, Integer requiredQuantity) throws NullPointerException {
        Storage product = repository.findById(id).orElse(null);
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

    public Boolean isOrderInStorage(HashMap<Long, Integer> cart) {
        for (HashMap.Entry<Long, Integer> entry : cart.entrySet()) {
            if (!this.isInStorage(entry.getKey(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public HashMap<String, Integer> findOutOfStorageProduct(HashMap<Long, Integer> cart) {
        HashMap<String, Integer> outOfStorageProduct = new HashMap<>();
        for (HashMap.Entry<Long, Integer> entry : cart.entrySet()) {
            if (!isInStorage(entry.getKey(), entry.getValue())) {
                Storage product = repository.findById(entry.getKey()).orElse(null);
                outOfStorageProduct.put(product.getName(), product.getQuantity());
            }
        }
        return outOfStorageProduct;
    }

}