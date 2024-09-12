package com.shop.storageservice.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OutMapAndEmailDTO {

    private Long customerId;
    private Map<ProductDuplicateDTO, Integer> outMap;
}
