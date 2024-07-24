package com.shop.productservice.Service;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Repository.CommentRepository;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;

    public List<Comment> findAllByProductId(Long productId) {
        return repository.findAllByProductId(productId);
    }








































}
