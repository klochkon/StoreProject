package com.shop.customerservice.Controller;

import com.shop.customerservice.Service.FavouriteCategoryService;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/favouriteCategory")
public class FavouriteCategoryController {

    private final FavouriteCategoryService service;

    @GetMapping("find/customer/{id}")
    public Set<String> findFavouriteCategoryByCustomerId(@PathVariable Long id) {
        return service.findFavouriteCategoryByCustomerId(id);
    }

    @PutMapping("set/customer/{id}")
    public void setFavouriteCategoryByCustomerId(@PathVariable Long id, @RequestBody Set<String> favouriteCategories) {
        service.setFavouriteCategoryByCustomerId(id, favouriteCategories);
    }
}
