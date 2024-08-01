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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    private Product product;

    @InjectMocks
    private ProductService service;

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
        LocalDate dateOfPublishing = LocalDate.of(2024, 8, 1);
        comment1.setDateOfPublishing(dateOfPublishing);
        comment1.setId(1L);
        product.setComment(comments);
        repository.save(product);
    }

    @Test
    void getAllProductTest() {
        List<Product> products = List.of(product);
        when(repository.findAll()).thenReturn(products);
        List<Product> testProducts = service.getAllProduct();
        assertEquals(products, testProducts);
        verify(repository, times(1)).findAll();
    }

    @Test
    void createProductTest() {
    when(repository.save(any(Product.class))).thenReturn(product);
    Product testProduct = service.createProduct(product);
    assertEquals(product, testProduct);
    verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProductTest() {
        when(repository.save(any(Product.class))).thenReturn(product);
        Product testProduct = service.updateProduct(product);
        assertEquals(product, testProduct);
        verify(repository, times(1)).save(any(Product.class));
    }

    @Test
    void deleteByIdTest() {
        assertTrue(repository.findById(product.getId()).isPresent());
        service.deleteById(product.getId());
        assertFalse(repository.findById(product.getId()).isPresent());
    }

    @Test
    void findByIdTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(product));
        Product testProduct = service.findById(product.getId());
        assertEquals(product, testProduct);
        verify(repository, times(1)).findById(anyLong());
    }

    @Test
    void findAllByCategoryTest() {
        List<Product> products = List.of(product);
        List<Product> testProducts = service.findAllByCategory(product.getCategory());
        assertEquals(products, testProducts);
        verify(repository, times(1)).findByCategory(anyString());
    }




}
