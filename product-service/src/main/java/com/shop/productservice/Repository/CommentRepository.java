package com.shop.productservice.Repository;

import com.shop.productservice.Model.Comment;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT comment FROM comment " +
                   "WHERE product_id = :productId", nativeQuery = true)
    List<Comment> findAllByProductId(@Param(value = "productId") Long productId);
}
