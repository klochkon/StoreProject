package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavouriteProductService {

    private final CustomerRepository repository;

    public Set<String> findFavouriteProductByCustomerId(Long id) throws NullPointerException {
        Customer customer = repository.findById(id).orElse(null);
        return customer.getFavouriteProduct();
    }

    public void setFavouriteProductByCustomerId(Long id, Set<String> favouriteProducts) throws NullPointerException {
        Customer customer = repository.findById(id).orElse(null);
        customer.setFavouriteProduct(favouriteProducts);
    }



}
