package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.FavouriteProduct;
import com.shop.customerservice.Service.FavouriteCategoryService;
import com.shop.customerservice.Service.FavouriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/favourite-product")
@RequiredArgsConstructor
public class FavouriteProductController {

    private final FavouriteProductService service;

    @GetMapping("find-by-customer-id-{customerId}")
    public List<String> findAllFavouriteProductByCustomerId(@PathVariable Long customerId) {
        return service.findAllFavouriteProductByCustomerId(customerId);
    }

    @PostMapping("add")
    public void addFavouriteProduct(@RequestBody FavouriteProduct favouriteProduct) {
        service.addFavouriteProduct(favouriteProduct);
    }

    @DeleteMapping("delete-by-id-{id}")
    public void deleteFavouriteProductById(@PathVariable Long id){
        service.deleteFavouriteProductById(id);
    }
}
