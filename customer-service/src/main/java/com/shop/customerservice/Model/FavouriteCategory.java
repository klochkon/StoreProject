package com.shop.customerservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Builder
@Table(name = "favourite_category")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteCategory {

    @Id
    @GeneratedValue
    private Long id;
    private String favouriteCategory;
    @ManyToMany(mappedBy = "customer")
    private Set<Customer> customer;
}
