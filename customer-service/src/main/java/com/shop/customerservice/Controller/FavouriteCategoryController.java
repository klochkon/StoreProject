package com.shop.customerservice.Controller;

import com.shop.customerservice.Service.FavouriteCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/favourite-category")
public class FavouriteCategoryController {

    private final FavouriteCategoryService service;

    @GetMapping("find-by-customer-id-{customerId}")
    public List<String> findAllFavouriteCategoryByCustomerId(@PathVariable Long customerId) {
        return service.findAllFavouriteCategoryByCustomerId(customerId);
    }

    @PostMapping("add-{favouriteCategory}-by-customer-{customerId}")
    public void addFavouriteCategoryByCustomerId(@PathVariable Long customerId, @PathVariable String favouriteCategory) {
        service.addFavouriteCategoryByCustomerId(customerId, favouriteCategory);
    }

    @DeleteMapping("delete-{favouriteCategory}-by-customer-{customerId}")
    public void deleteFavouriteCategoryByCustomerId(@PathVariable Long customerId, @PathVariable String favouriteCategory){
        service.deleteFavouriteCategoryByCustomerId(customerId, favouriteCategory);
    }


}
