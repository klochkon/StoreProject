package com.shop.userservice.Service;

import com.shop.userservice.Model.User;
import com.shop.userservice.Repository.FavouriteCategoryRepository;
import com.shop.userservice.Repository.FavouriteProductRepository;
import com.shop.userservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FavouriteCategoryRepository favouriteCategoryRepository;
    private final FavouriteProductRepository favouriteProductRepository;

//    @CachePut(value = "user", key = "#id") if i`ll need get queries
    public User saveUser(User user) {
        return userRepository.save(user);
    }

//    @CachePut(value = "user", key = "#id") if i`ll need get queries
    public User updateUser(User user) {
        return userRepository.save(user);
    }

//    @CacheEvict(value = "user", key = "#id") if i`ll need get queries
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<String> findAllFavouriteCategorie() {return favouriteCategoryRepository.findAll();}









}
