package com.shop.productservice.Service;

import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    @Cacheable(value = "allProduct")
    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    @CachePut(value = {"allProduct", "product"}, key = "#product.id")
    public Product createProduct(Product product) {
        return repository.save(product);
    }


    @CachePut(value = {"product", "allProduct"}, key = "#product.id")
    public Product updateProduct(Product product) {
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

    @Cacheable(value = "allProduct", key = "#category")
    public List<Product> findAllByCategory(String category) {
        return repository.findByCategory(category);
    }
}
