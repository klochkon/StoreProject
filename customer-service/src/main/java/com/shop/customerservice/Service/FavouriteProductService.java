package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavouriteProductService {

    private final CustomerRepository repository;

    @Cacheable(value = "favoriteProduct", key = "#customerId")
    public Set<String> findFavouriteProductByCustomerId(Long customerId) {
        Customer customer = repository.findById(customerId).orElse(null);
        return customer.getFavouriteProduct();
    }

    @CachePut(value = "favoriteProduct", key = "#customerId")
    public void setFavouriteProductByCustomerId(Long customerId, Set<String> favouriteProducts) {
        Customer customer = repository.findById(customerId).orElse(null);
        customer.setFavouriteProduct(favouriteProducts);
    }



}
