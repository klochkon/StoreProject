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

    public List<Product> GetAllProduct() { return repository.findAll();}

    public Product CreateProduct(Product product) {return repository.save(product);}

    public Product UpdateProduct(Product product) {return repository.save(product);}

    public Product deleteProductById(Long id) {return repository.DeleteProductById(id);}

}
