package com.shop.customerservice.Model;


import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "Customer")
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

    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FavouriteCategory> favouriteCategory;


    @OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FavouriteProduct> favouriteProduct;
}
