package com.shop.userservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "FavouriteCategorie")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FavouriteCategorie {

    @Id
    private Long id;
    private String favouriteCategory;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
