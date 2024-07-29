package com.shop.customerservice.Controller;

import com.shop.customerservice.Service.FavouriteProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/favouriteProduct")
public class FavouriteProductController {

    private final FavouriteProductService service;

    @GetMapping("find/customer/{id}")
    public Set<String> findFavouriteProductByCustomerId(@PathVariable Long id) {
        return service.findFavouriteProductByCustomerId(id);
    }

    @PutMapping("set/customer/{id}")
    public void setFavouriteProductByCustomerId(@PathVariable Long id, @RequestBody Set<String> favouriteProducts) {
        service.setFavouriteProductByCustomerId(id, favouriteProducts);
    }
}
