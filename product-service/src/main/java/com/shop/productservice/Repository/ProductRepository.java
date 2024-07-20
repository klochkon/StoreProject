package com.shop.productservice.Repository;

import com.shop.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product deleteProductById(Long id);
    public Product findProductById(Long id);
    public List<Product> findProductByCategorie(String categorie);
}
