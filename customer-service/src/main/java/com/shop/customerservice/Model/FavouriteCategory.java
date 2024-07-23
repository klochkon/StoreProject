package com.shop.customerservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "FavouriteCategory")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteCategory {

    @Id
    private Long id;
    private String favouriteCategory;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
}
