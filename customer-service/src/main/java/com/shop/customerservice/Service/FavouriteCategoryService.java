package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavouriteCategoryService {

    private final CustomerRepository repository;

    public Set<String> findFavouriteCategoryByCustomerId(Long id) throws NullPointerException {
        Customer customer = repository.findById(id).orElse(null);
        return customer.getFavouriteCategory();
    }

    public void setFavouriteCategoryByCustomerId(Long id, Set<String> favouriteCategories) throws NullPointerException {
        Customer customer = repository.findById(id).orElse(null);
        customer.setFavouriteCategory(favouriteCategories);
    }


}
