package com.shop.customerservice.Service;


import com.shop.customerservice.Model.FavouriteProduct;
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

    public void addFavouriteProduct(FavouriteProduct favouriteProduct) {
        repository.save(favouriteProduct);
    }

    public void deleteFavouriteProductById(Long id){
        repository.deleteById(id);
    }
}
