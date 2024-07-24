package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.Customer;
import com.shop.customerservice.Model.FavouriteProduct;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
    @Query(value = "SELECT favourite_product FROM favourite_product " +
            "WHERE customer_id = :customerId", nativeQuery = true)
    List<String> findFavouriteProductByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "INSERT INTO favourite_product (favourite_product, customer_id) " +
            "VALUES (:favouriteProduct, :customerId)", nativeQuery = true)
    void addFavouriteProductByCustomerId(@Param("customerId") Long customerId, @Param("favouriteProduct") String favouriteProduct);

    @Query(value = "DELETE FROM FavouriteProduct " +
            "WHERE customer_id = :customerId AND favourite_product = :favouriteProduct", nativeQuery = true)
    void deleteFavouriteProductByCustomerId(@Param("customerId")Long customerId, @Param("favouriteProduct") String favouriteProduct);
}

