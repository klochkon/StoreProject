package com.shop.customerservice.Repository;

import com.shop.customerservice.Model.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
//    @Query(value = "SELECT favouriteProduct FROM FavouriteProduct" +
//    "WHERE id =: customer")
//    List<String> findAllStringFavouriteProductByUserId(Long customer);

}
