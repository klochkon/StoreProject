package com.shop.customerservice.Controller;

import com.shop.customerservice.Model.FavouriteCategory;
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

    @PostMapping("add-{favouriteCategory}")
    public void addFavouriteCategory(@RequestBody FavouriteCategory favouriteCategory) {
        service.addFavouriteCategory(favouriteCategory);
    }

    @DeleteMapping("delete-{id}")
    public void deleteFavouriteCategoryById(@PathVariable Long id){
        service.deleteFavouriteCategoryById(id);
    }


}
