package com.shop.productservice.Service;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    private Product product;

    private Comment comment;

    @InjectMocks
    private ProductService service;

    @BeforeEach
    public void setUp() {
        List<Comment> comments = new ArrayList<>();
        MockitoAnnotations.openMocks(this);
        LocalDate dateOfPublishing = LocalDate.of(2024, 8, 1);

        comment = Comment.builder()
                .product(product)
                .comment("comment")
                .authorNickname("authorNickname")
                .dateOfPublishing(dateOfPublishing)
                .id(1L)
                .build();
        comments.add(comment);

        product = Product.builder()
                .id(1L)
                .category("category")
                .cost(new BigDecimal(100.00))
                .name("name")
                .producer("producer")
                .description("description")
                .feedBack(new BigDecimal(1.1))
                .comment(comments)
                .build();
    }

//    @Test
//    void getAllProduct() {
//        List<Product> products = List.of(product);
//        when(repository.findAll()).thenReturn(products);
//        List<Product> testProducts = service.getAllProduct();
//        assertEquals(products, testProducts);
//        verify(repository, times(1)).findAll();
//    }

    @Test
    void createProduct() {
        when(repository.save(any(Product.class))).thenReturn(product);
        Product testProduct = service.createProduct(product);
        assertEquals(product, testProduct);
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProduct() {
        when(repository.save(any(Product.class))).thenReturn(product);
        Product testProduct = service.updateProduct(product);
        assertEquals(product, testProduct);
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteById() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteById(product.getId());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        Product testProduct = service.findById(product.getId());
        assertEquals(product, testProduct);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findAllByCategory() {
        List<Product> products = List.of(product);
        when(repository.findAllByCategory(anyString())).thenReturn(products);
        List<Product> testProducts = service.findAllByCategory(product.getCategory());
        assertEquals(products, testProducts);
        verify(repository, times(1)).findAllByCategory(anyString());
    }
}
