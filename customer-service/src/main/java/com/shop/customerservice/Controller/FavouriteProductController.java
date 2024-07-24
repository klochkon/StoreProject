package com.shop.customerservice.Controller;

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

    @PostMapping("add-{favouriteProduct}-by-customer-{customerId}")
    public void addFavouriteProductByCustomerId(@PathVariable Long customerId, @PathVariable String favouriteProduct) {
        service.addFavouriteProductByCustomerId(customerId, favouriteProduct);
    }

    @DeleteMapping("delete-{favouriteProduct}-by-customer-{customerId}")
    public void deleteFavouriteProductByCustomerId(@PathVariable Long customerId, @PathVariable String favouriteProduct){
        service.deleteFavouriteProductByCustomerId(customerId, favouriteProduct);
    }
}
