package com.shop.customerservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "favourite_product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FavouriteProduct {

    @Id
    @GeneratedValue
    private Long id;
    private String favouriteProduct;

    @ManyToMany(mappedBy = "customer")
    private Set<Customer> customer;



}
