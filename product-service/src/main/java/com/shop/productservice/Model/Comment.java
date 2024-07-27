package com.shop.productservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "comment")
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
    @JoinColumn(name = "product_id")
    private Product product;






}
