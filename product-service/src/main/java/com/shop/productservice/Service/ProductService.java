package com.shop.productservice.Service;

import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository repository;

    public List<Product> getAllProduct() { return repository.findAll();}

    public Product createProduct(Product product) {return repository.save(product);}

    public Product UpdateProduct(Product product) {return repository.save(product);}

    public void deleteById(Long id) {repository.deleteById(id);}

    public Product findById(Long id) {return repository.findById(id).orElse(null);}

    public List<Product> findByCategory(String category) {
        return repository.findByCategory(category);
    }
}
