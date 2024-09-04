package com.shop.purchaseservice.DTO;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDTO {

    private Long id;
    private Long customerId;
    private Map<String, Integer> cart;
    private BigDecimal cost;

}