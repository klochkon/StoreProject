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
public class FavouriteCategoryService {

    private final CustomerRepository repository;

    @Cacheable(value = "favoriteCategory", key = "#customerId")
    public Set<String> findFavouriteCategoryByCustomerId(Long customerId) throws NullPointerException {
        Customer customer = repository.findById(customerId).orElse(null);
        return customer.getFavouriteCategory();
    }

    @CachePut(value = "favoriteCategory", key = "#customerId")
    public void setFavouriteCategoryByCustomerId(Long customerId, Set<String> favouriteCategories) throws NullPointerException {
        Customer customer = repository.findById(customerId).orElse(null);
        customer.setFavouriteCategory(favouriteCategories);
    }


}
