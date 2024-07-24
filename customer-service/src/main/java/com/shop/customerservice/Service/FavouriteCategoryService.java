package com.shop.customerservice.Service;

import com.shop.customerservice.Repository.FavouriteCategoryRepository;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteCategoryService {

    private final FavouriteCategoryRepository repository;

    public List<String> findAllFavouriteCategoryByCustomerId(Long customerId) {
        return repository.findFavouriteCategoryByCustomerId(customerId);
    }

    public void addFavouriteCategoryByCustomerId(Long customerId, String favouriteCategory) {
        repository.addFavouriteCategoryByCustomerId(customerId, favouriteCategory);
    }

    public void deleteFavouriteCategoryByCustomerId(Long customerId, String favouriteCategory){
        repository.deleteFavouriteCategoryByCustomerId(customerId, favouriteCategory);
    }

}
