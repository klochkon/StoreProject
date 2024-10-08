package com.shop.productservice.Controller;


import com.shop.productservice.DTO.ProductWithQuantityDTO;
import com.shop.productservice.DTO.StorageDuplicateDTO;
import com.shop.productservice.Model.Product;
import com.shop.productservice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    ProductService service;

    @GetMapping("get/all")
    public List<ProductWithQuantityDTO> getAllProductWithQuantity(@RequestBody List<StorageDuplicateDTO> storageList) {
        return service.getAllProductWithQuantity(storageList);
    }

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }



    @PutMapping("update")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("delete/{id}")
    public void deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @GetMapping("share/{slug}")
    public Product shareProduct(@PathVariable String slug) {
        return service.findBySlug(slug);
    }


    @GetMapping("get/{id}")
    public Product getProductById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("get/category/{category}")
    public List<Product> findProductByCategory(@PathVariable String category) {
        return service.findAllByCategory(category);
    }
}
