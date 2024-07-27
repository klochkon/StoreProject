package com.shop.customerservice.Model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String nickname;
    private String name;
    private String surname;
    private String sex;
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "customer_favourite_category",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "favourite_category_id"))
    private Set<FavouriteCategory> favouriteCategory;

    @ManyToMany
    @JoinTable(name = "customer_favourite_product",
    joinColumns = @JoinColumn(name = "customer_id"),
    inverseJoinColumns = @JoinColumn(name = "favourite_product_id"))
    private Set<FavouriteProduct> favouriteProduct;

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Order> order;



}
