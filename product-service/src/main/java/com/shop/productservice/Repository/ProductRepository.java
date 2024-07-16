package com.shop.productservice.Repository;

import com.shop.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product DeleteProductById(Long id);

}
