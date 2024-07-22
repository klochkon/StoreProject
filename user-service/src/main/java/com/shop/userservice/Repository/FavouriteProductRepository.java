package com.shop.userservice.Repository;

import com.shop.userservice.Model.FavouriteProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteProductRepository extends JpaRepository<FavouriteProduct, Long> {
}
