package com.shop.storageservice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Storage")
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Storage {

    @Id
    @GeneratedValue
    private Long id;
    private Integer quantity;
    private String name;


}
