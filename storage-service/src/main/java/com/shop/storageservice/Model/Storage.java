package com.shop.storageservice.Model;

import com.shop.storageservice.DTO.ProductDuplicateDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
//    id - id in ProductDuplicateDTO
    private Long id;
    private Integer quantity;
}
