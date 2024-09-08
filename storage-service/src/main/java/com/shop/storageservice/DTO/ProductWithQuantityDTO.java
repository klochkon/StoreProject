package com.shop.storageservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductWithQuantityDTO {

    private Long id;

    private String description;
    private BigDecimal cost;
    private String name;
    private String producer;
    private String category;

    private Double feedBack;

    private Integer quantity;
}
