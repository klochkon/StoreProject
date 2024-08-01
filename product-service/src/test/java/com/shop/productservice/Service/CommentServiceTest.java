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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository repository;

    private Comment comment;

    private Product product;

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
        when(repository.findAllByProductId(anyLong())).thenReturn(comments);
        List<Comment> testComments = service.findAllByProductId(product.getId());
        assertEquals(comments, testComments);
        verify(repository, times(1)).findAllByProductId(anyLong());

    }

    @Test
    void addComment() {
        when(repository.save(any(Comment.class))).thenReturn(comment);
        Comment testComment = service.addComment(comment);
        assertEquals(comment, testComment);
        verify(repository, times(1)).save(any(Comment.class));
    }

    @Test
    void updateComment() {
        when(repository.save(any(Comment.class))).thenReturn(comment);
        Comment testComment = service.updateComment(comment);
        assertEquals(comment, testComment);
        verify(repository, times(1)).save(any(Comment.class));
    }

    @Test
    void deleteCommentById() {
        doNothing().when(repository).deleteById(anyLong());
        service.deleteCommentById(comment.getId());
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAllByAuthorNickname() {
        List<Comment> comments = List.of(comment);
        when(repository.findAllByAuthorNickname(anyString())).thenReturn(comments);
        List<Comment> testComments = service.findAllByAuthorNickname(comment.getAuthorNickname());
        assertEquals(comments, testComments);
        verify(repository, times(1)).findAllByAuthorNickname(anyString());
    }
}
