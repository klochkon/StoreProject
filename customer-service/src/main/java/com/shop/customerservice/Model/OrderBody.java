package com.shop.customerservice.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_body")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBody {
    @Id
    private Long id;
    private Integer quantity;
    private String product;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
