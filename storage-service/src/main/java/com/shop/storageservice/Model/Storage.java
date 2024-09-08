package com.shop.storageservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "storage")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Storage {

    @Id
//    id - id of ProductDuplicateDTO
    private Long productId;
    private Integer quantity;
}
