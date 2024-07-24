package com.shop.storageservice.Repository;

import com.shop.storageservice.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface StorageRepository extends JpaRepository<Storage, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Storage" +
            "SET quantity = quantity + quantityAdded" +
            "WHERE id = addedId",
            nativeQuery = true)
    void addProductById(Long addedId, Integer quantityAdded);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Storage" +
            "SET quantity = quantity - quantityDeleted" +
            "WHERE id = deletedId",
            nativeQuery = true)
    void deleteproductById(Long deletedId, Integer quantityDeleted);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Storage " +
            "SET quantity = quantity + 1 " +
            "WHERE id = :addedId",
            nativeQuery = true)
    void addProductByIdByOne(@Param("addedId")Long addedId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Storage " +
            "SET quantity = quantity - 1 " +
            "WHERE id = :addedId",
            nativeQuery = true)
    void deleteProductByIdByOne(@Param("deletedId")Long deletedId);

}
