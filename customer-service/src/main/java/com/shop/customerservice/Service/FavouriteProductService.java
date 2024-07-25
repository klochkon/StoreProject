package com.shop.customerservice.Service;


import com.shop.customerservice.Model.FavouriteProduct;
import com.shop.customerservice.Repository.FavouriteProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteProductService {

    private final FavouriteProductRepository repository;

    @Cacheable(value = "allFavouriteProduct")
    public List<String> findAllFavouriteProductByCustomerId(Long customerId) {
        return repository.findFavouriteProductByCustomerId(customerId);
    }

    @CachePut(value = "allFavouriteProduct", key = "#favouriteProduct.id")
    public void addFavouriteProduct(FavouriteProduct favouriteProduct) {
        repository.save(favouriteProduct);
    }

    @CacheEvict(value = "allFavouriteProduct", key = "#id")
    public void deleteFavouriteProductById(Long id){
        repository.deleteById(id);
    }
}
