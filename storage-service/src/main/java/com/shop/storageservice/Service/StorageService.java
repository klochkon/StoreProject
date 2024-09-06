package com.shop.storageservice.Service;

import com.shop.storageservice.DTO.ProductDuplicateDTO;
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

    @Cacheable(value = "is-in-storage", key = "#id")
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
        repository.deleteProductById(deletedId, quantityDeleted);
    }

    public Boolean isOrderInStorage(Map<ProductDuplicateDTO, Integer> cart) {
        for (Map.Entry<ProductDuplicateDTO, Integer> entry : cart.entrySet()) {
            if (!this.isInStorage(entry.getKey().getId(), entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    public Map<ProductDuplicateDTO, Integer> findOutOfStorageProduct(Map<ProductDuplicateDTO, Integer> cart) {
        Map<ProductDuplicateDTO, Integer> outOfStorageProduct = new HashMap<>();
        for (Map.Entry<ProductDuplicateDTO, Integer> entry : cart.entrySet()) {
            if (!isInStorage(entry.getKey().getId(), entry.getValue())) {
                Storage product = repository.findById(entry.getKey().getId()).orElse(null);
                outOfStorageProduct.put(entry.getKey(), product.getQuantity());
            }
        }
        return outOfStorageProduct;
    }

}