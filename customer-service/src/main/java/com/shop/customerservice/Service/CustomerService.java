package com.shop.customerservice.Service;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Repository.FavouriteCategoryRepository;
import com.shop.customerservice.Repository.FavouriteProductRepository;
import com.shop.customerservice.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FavouriteCategoryRepository favouriteCategoryRepository;
    private final FavouriteProductRepository favouriteProductRepository;

    //    @CachePut(value = "customer", key = "#id") if i`ll need get queries
    public Customer saveUser(Customer customer) {
        return customerRepository.save(customer);
    }

    //    @CachePut(value = "customer", key = "#id") if i`ll need get queries
    public Customer updateUser(Customer customer) {
        return customerRepository.save(customer);
    }

    //    @CacheEvict(value = "customer", key = "#id") if i`ll need get queries
    public void deleteUserById(Long id) {customerRepository.deleteById(id);}

    public List<String> findAllFavouriteCategory() {
        return favouriteCategoryRepository.findAllStringFavouriteCategory();
    }

//    public List<String> findAllStringFavouriteProduct() {
//        return favouriteProductRepository.findAllStringFavouriteProduct();}

//    public FavouriteCategory saveFavouriteCategory(FavouriteCategorie favouriteCategorie) {}


}
