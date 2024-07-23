package com.shop.userservice.Repository;

import com.shop.userservice.Model.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
    @Query(value = "SELECT favouriteProduct FROM FavouriteProduct ")
    List<String> findAllStringFavouriteProduct();

}
