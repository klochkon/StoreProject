package com.shop.customerservice.Service;


import com.shop.customerservice.Repository.FavouriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteProductService {

    private final FavouriteProductRepository repository;

    public List<String> findAllFavouriteProductByCustomerId(Long customerId) {
        return repository.findFavouriteProductByCustomerId(customerId);
    }

    public void addFavouriteProductByCustomerId(Long customerId, String favouriteProduct) {
        repository.addFavouriteProductByCustomerId(customerId, favouriteProduct);
    }

    public void deleteFavouriteProductByCustomerId(Long customerId, String favouriteProduct){
        repository.deleteFavouriteProductByCustomerId(customerId, favouriteProduct);
    }
}
