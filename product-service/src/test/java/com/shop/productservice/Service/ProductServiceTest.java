package com.shop.productservice.Service;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setId(1L);
        product.setCategory("category");
        product.setCost(1L);
        product.setName("name");
        product.setProducer("producer");
        product.setDescription("description");
        product.setFeedBack(1.1);

        List<Comment> comments = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setProduct(product);
        comment1.setComment("comment");
        comment1.setAuthorNickname("authorNickname");
//    comment1.setDateOfPublishing(2024, 8, 1);
//    }
//
//    @Test
//    List<Product> getAllProductTest() {
//    when(productRepository.findAll()).thenReturn()
//    }

    }



























}
