package com.shop.productservice.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String description;
    private Long cost;
    private String name;
    private String producer;
    private String category;

    @DecimalMin(value = "1.0", inclusive = true, message = "The minimum value is 1.0")
    @DecimalMax(value = "5.0", inclusive = true, message = "The maximum value is 5.0")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal feedBack;

    @OneToMany(mappedBy = "product", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Comment> comment;


}



