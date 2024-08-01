package com.shop.productservice.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
    private LocalDate dateOfPublishing;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;






}
