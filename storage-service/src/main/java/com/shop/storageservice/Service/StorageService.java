package com.shop.storageservice.Service;

import com.shop.storageservice.Client.ProductClient;
import com.shop.storageservice.DTO.OrderDuplicateDTO;
import com.shop.storageservice.DTO.ProductDuplicateDTO;
import com.shop.storageservice.DTO.ProductWithQuantityDTO;
import com.shop.storageservice.DTO.StorageDuplicateDTO;
import com.shop.storageservice.Model.Storage;
import com.shop.storageservice.Repository.StorageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class StorageService {

    private final KafkaTemplate<String, List<StorageDuplicateDTO>> kafkaProductVerification;
    private final StorageRepository repository;
    private final ProductClient productClient;

    @PersistenceContext
    private EntityManager entityManager;

    @CachePut(value = "storage", key = "#productDuplicateDTO.id")
    public void saveProduct(Integer quantity, ProductDuplicateDTO productDuplicateDTO) {
        Storage storage;
        storage = Storage.builder()
                .productId(productDuplicateDTO.getId())
                .quantity(quantity)
                .build();
        repository.save(storage);
    }

    @CachePut(value = "storage", key = "#productDuplicateDTO.id")
    public void updateProduct(Integer quantity, ProductDuplicateDTO productDuplicateDTO) {
        Storage storage;
        storage = Storage.builder()
                .productId(productDuplicateDTO.getId())
                .quantity(quantity)
                .build();
        repository.save(storage);
    }



    public List<ProductWithQuantityDTO> findAllStorageWithQuantity() {
        List<Storage> allProducts = repository.findAll();
        List<StorageDuplicateDTO> storageList = new ArrayList<>();
            for(Storage product : allProducts) {
                StorageDuplicateDTO storageDuplicateDTO = new StorageDuplicateDTO();
                storageDuplicateDTO = StorageDuplicateDTO.builder()
                        .customerId(product.getProductId())
                        .quantity(product.getQuantity())
                        .build();
            }
        return productClient.getAllProductWithQuantity(storageList);

        }

    @CacheEvict(value = "storage", key = "#id")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

//    todo verification
    @Scheduled(cron = "0 0 7 * * ?")
    public void productVerification() {
        List<Storage> allProducts= repository.findAll();
        List<StorageDuplicateDTO> productsWithLack = new ArrayList<>();
        for(Storage product : allProducts) {
            if (product.getQuantity() <= 10) {
                StorageDuplicateDTO storageDuplicateDTO;
                storageDuplicateDTO = StorageDuplicateDTO.builder()
                        .customerId(product.getProductId())
                        .quantity(product.getQuantity())
                        .build();
                productsWithLack.add(storageDuplicateDTO);
            }
        }
        kafkaProductVerification.send("product-name-identifier-topic", productsWithLack);
    }

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

    @KafkaListener(topics = "order-topic", groupId = "${spring.kafka.consumer-groups.order-group.group-id}")
    @CacheEvict(value = "storage", key = "#orderDuplicateDTO.id")
    public void deleteProductById(OrderDuplicateDTO orderDuplicateDTO) {
        for (Map.Entry<ProductDuplicateDTO, Integer> entry : orderDuplicateDTO.getCart().entrySet()) {
            entityManager.createNativeQuery("UPDATE storage " +
                            "SET quantity = quantity - :deletedQuantity " +
                            "WHERE id = :id")
                    .setParameter("deletedQuantity", entry.getValue())
                    .setParameter("id", entry.getKey().getId())
                    .executeUpdate();
        entityManager.flush();
        entityManager.clear();
        }
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