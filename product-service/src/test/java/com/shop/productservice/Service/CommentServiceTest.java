package com.shop.productservice.Service;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Model.Product;
import com.shop.productservice.Repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository repository;

    private Comment comment;

    private  CommentService service;

    @BeforeEach
    public void setUp() {

        List<Comment> comments = List.of(comment);

        Product product = new Product();
        product.setId(1L);
        product.setCategory("category");
        product.setCost(1L);
        product.setName("name");
        product.setProducer("producer");
        product.setDescription("description");
        product.setFeedBack(1.1);
        product.setComment(comments);

        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setComment("comment");
        comment.setAuthorNickname("authorNickname");
        LocalDate dateOfPublishing = LocalDate.of(2024, 8, 1);
        comment.setDateOfPublishing(dateOfPublishing);
        comment.setId(1L);
    }

    @Test
    void findAllByProductId() {
        List<Comment> comments = List.of(comment);
//        List<Comment> testComments = service.findAllByProductId();
    }













}
