package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Model.FavouriteCategory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteCategoryRepository extends JpaRepository<FavouriteCategory, Long> {

    @Query(value = "SELECT favourite_category FROM favourite_category " +
            "WHERE customer_id = :customerId", nativeQuery = true)
    List<String> findFavouriteCategoryByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "INSERT INTO favourite_category (favourite_category, customer_id) " +
            "VALUES (:favouriteCategory, :customerId)", nativeQuery = true)
    void addFavouriteCategoryByCustomerId(@Param("customerId") Long customerId, @Param("favouriteCategory") String favouriteCategory);

    @Query(value = "DELETE FROM FavouriteCategory " +
            "WHERE customer_id = :customerId AND favourite_category = :favouriteCategory", nativeQuery = true)
    void deleteFavouriteCategoryByCustomerId(@Param("customerId")Long customerId, @Param("favouriteCategory") String favouriteCategory);




}