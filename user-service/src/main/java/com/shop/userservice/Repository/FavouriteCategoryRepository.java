package com.shop.userservice.Repository;

import com.shop.userservice.Model.FavouriteCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteCategoryRepository extends JpaRepository<FavouriteCategorie, Long> {

    @Query(value = "SELECT favouriteCategory FROM favouriteCategory")
    List<String> findAllStringFavouriteCategories();


}
