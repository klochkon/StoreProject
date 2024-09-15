package com.shop.productservice.Model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "product")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductElasticsearch {

    @Id
    private Long id;

    private String description;
    private BigDecimal cost;
    private String name;
    private String producer;
    private String category;
}
