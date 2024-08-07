package com.shop.productservice.Service;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    @Cacheable(value = "allProductComment")
    public List<Comment> findAllByProductId(Long productId) {
        return repository.findAllByProductId(productId);
    }

    @CachePut(value = "allProductComment", key = "#comment.id")
    public Comment addComment(Comment comment) {
        return repository.save(comment);
    }

    @CachePut(value = "allProductComment", key = "#comment.id")
    public Comment updateComment(Comment comment) {
        return repository.save(comment);
    }

    @CacheEvict(value = "allProductComment", key = "#id")
    public void deleteCommentById(Long id) {
        repository.deleteById(id);
    }

    @Cacheable(value = "allProductComment")
    public List<Comment> findAllByAuthorNickname(String authorNickname) {
        return repository.findAllByAuthorNickname(authorNickname);
    }


}
