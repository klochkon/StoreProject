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


    @Query(value = "DELETE FROM favourite_category WHERE customer_id = :customerId", nativeQuery = true)
    void deleteAllByCustomerId(@Param("customerId") Long customerId);
}