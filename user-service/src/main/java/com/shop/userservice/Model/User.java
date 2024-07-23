package com.shop.userservice.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String surname;
    private String sex;
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FavouriteCategorie> favouriteCategorie;


    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FavouriteProduct> favouriteProduct;
}
