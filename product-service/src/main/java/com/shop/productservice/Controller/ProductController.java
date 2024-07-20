package com.shop.productservice.Controller;


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

    @GetMapping("get-all")
    public List<Product> getAllProduct() {return service.GetAllProduct();}

    @PostMapping("create")
    public Product createProduct(@RequestBody Product product) {return service.CreateProduct(product);}

    @PutMapping("update")
    public Product updateProduct(@RequestBody Product product) {return service.UpdateProduct(product);}

    @DeleteMapping("delete-by-{id}")
    public Product deleteProductById(@PathVariable Long id) {return service.deleteProductById(id);}

    @GetMapping("get-with-id-{id}")
    public Product getProductById(@PathVariable Long id) {return service.findProductById(id);}

    @GetMapping("get-all-{categorie}")
    public List<Product> findProductByCategorie(@PathVariable String categorie) {
        return service.findProductByCategorie(categorie);
    }

}
