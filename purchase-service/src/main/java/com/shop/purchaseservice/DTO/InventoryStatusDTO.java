package com.shop.purchaseservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryStatusDTO {

    private Boolean isOrderInStorage;
    private Map<String ,Integer> outOfStorageProducts;




}
