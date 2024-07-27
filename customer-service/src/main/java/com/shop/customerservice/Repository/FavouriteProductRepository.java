package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Model.FavouriteProduct;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
    @Query(value = "SELECT favourite_product FROM favourite_product " +
            "WHERE customer_id = :customerId", nativeQuery = true)
    List<String> findFavouriteProductByCustomerId(@Param("customerId") Long customerId);

}

