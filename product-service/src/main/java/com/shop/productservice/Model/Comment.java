package com.shop.productservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Comment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private Long id;
    private String authorNickname;
    private Date dateOfPublishing;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;






}
