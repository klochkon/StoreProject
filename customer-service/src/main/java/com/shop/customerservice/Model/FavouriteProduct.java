package com.shop.customerservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FavouriteProduct")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FavouriteProduct {

    @Id
    @GeneratedValue
    private Long id;
    private String favouriteProduct;

    @ManyToOne()
    @JoinColumn(name = "customerId")
    private Customer customer;



}
