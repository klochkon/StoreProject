package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.FavouriteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteCategoryRepository extends JpaRepository<FavouriteCategory, Long> {

    @Query(value = "SELECT favouriteCategory FROM FavouriteCategory")
    List<String> findAllStringFavouriteCategory();


}
