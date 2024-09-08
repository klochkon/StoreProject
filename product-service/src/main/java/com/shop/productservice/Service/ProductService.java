package com.shop.productservice.Service;

import com.shop.productservice.DTO.MailDTO;
import com.shop.productservice.DTO.ProductWithQuantityDTO;
import com.shop.productservice.DTO.StorageDuplicateDTO;
import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;
    private final KafkaTemplate<String, String> kafkaNewProduct;
    private final KafkaTemplate<String, MailDTO> kafkaVerification;


    @Cacheable(value = "productWithQuantity")
    public List<ProductWithQuantityDTO> getAllProductWithQuantity(List<StorageDuplicateDTO> storageList) {
        List<Product> products = repository.findAll();
        List<ProductWithQuantityDTO> resultList = new ArrayList<>();

        Map<Long, Integer> storageMap = new HashMap<>();
        for (StorageDuplicateDTO storageDuplicateDTO : storageList) {
            storageMap.put(storageDuplicateDTO.getCustomerId(), storageDuplicateDTO.getQuantity());
        }

        for (Product product : products) {
            ProductWithQuantityDTO productWithQuantityDTO;
            productWithQuantityDTO = ProductWithQuantityDTO.builder()
                    .quantity(storageMap.get(product.getId()))
                    .id(product.getId())
                    .name(product.getName())
                    .category(product.getCategory())
                    .cost(product.getCost())
                    .description(product.getDescription())
                    .feedBack(product.getFeedBack())
                    .producer(product.getProducer())
                    .build();
            resultList.add(productWithQuantityDTO);
        }
        return resultList;
    }

    //    todo method
    @KafkaListener(topics = "product-name-identifier-topic", groupId = "${spring.kafka.consumer-groups.product-verification-group.group-id}")
    public void nameIdentifier(List<StorageDuplicateDTO> productsWithLack) {
        MailDTO mailDTO = new MailDTO();
        Map<String, Object> data = new HashMap<>();
        List<Product> products = repository.findAll();

        Map<Long, String> productMap = new HashMap<>();
        for (Product product : products) {
            productMap.put(product.getId(), product.getName());
        }

        Map<String, Integer> LackMap = new HashMap<>();
        for (StorageDuplicateDTO storageDuplicateDTO : productsWithLack) {
            LackMap.put(productMap.get(storageDuplicateDTO.getCustomerId()), storageDuplicateDTO.getQuantity());
        }

        data.put("MapOfLackProducts", LackMap);
        mailDTO.setData(data);
        kafkaVerification.send("product-verification-topic", mailDTO);

    }

    @CachePut(value = {"allProduct", "product"}, key = "#product.id")
    public Product createProduct(Product product) {
//     todo when start project and make a lot of products comment it and end functionality
        kafkaNewProduct.send("new-product-topic", product.getCategory());
        return repository.save(product);
    }

    @CacheEvict(value = {"product", "allProduct"}, key = "#id")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "product", key = "#id")
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @CachePut(value = {"product", "allProduct"}, key = "#product.id")
    public Product updateProduct(Product product) {
        return repository.save(product);
    }

    @Cacheable(value = "allProduct", key = "#category")
    public List<Product> findAllByCategory(String category) {
        return repository.findAllByCategory(category);
    }
}
