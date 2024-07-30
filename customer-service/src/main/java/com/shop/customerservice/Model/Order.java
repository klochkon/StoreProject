package com.shop.customerservice.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "order")
public class Order {

    @Id
    private Long id;
    private Long customerId;
    private HashMap<Long, Integer> cart;
}
