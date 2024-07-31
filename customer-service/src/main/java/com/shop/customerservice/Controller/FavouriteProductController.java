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

    @GetMapping("find/customer/{customerId}")
    public Set<String> findFavouriteProductByCustomerId(@PathVariable Long customerId) {
        return service.findFavouriteProductByCustomerId(customerId);
    }

    @PutMapping("set/customer/{customerId}")
    public void setFavouriteProductByCustomerId(@PathVariable Long customerId, @RequestBody Set<String> favouriteProducts) {
        service.setFavouriteProductByCustomerId(customerId, favouriteProducts);
    }
}
