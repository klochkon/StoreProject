package com.shop.customerservice.Service;

import com.shop.customerservice.Model.FavouriteCategory;
import com.shop.customerservice.Repository.FavouriteCategoryRepository;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavouriteCategoryService {

    private final FavouriteCategoryRepository repository;

    @Cacheable(value = "allFavouriteCategories")
    public List<String> findAllFavouriteCategoryByCustomerId(Long customerId) {
        return repository.findFavouriteCategoryByCustomerId(customerId);
    }

    @CachePut(value = "allFavouriteCategories", key = "#favouriteCategory.id")
    public void addFavouriteCategory(FavouriteCategory favouriteCategory) {
        repository.save(favouriteCategory);
    }

    @CacheEvict(value = "allFavouriteCategories", key = "#id")
    public void deleteFavouriteCategoryById(Long id) {
        repository.deleteById(id);
    }
}