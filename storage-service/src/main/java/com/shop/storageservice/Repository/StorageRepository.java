package com.shop.storageservice.Repository;

import com.shop.storageservice.Model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<Storage,Long> {
    Boolean IsInStorage(Long id);
}
