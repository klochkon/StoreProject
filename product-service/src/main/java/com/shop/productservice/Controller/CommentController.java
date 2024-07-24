package com.shop.productservice.Controller;

import com.shop.productservice.Model.Comment;
import com.shop.productservice.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;

    @GetMapping("find-by-product-{productId}")
    public List<Comment> findAllByProductId(@PathVariable Long productId) {
        return service.findAllByProductId(productId);
    }

    @PostMapping("add")
    public void addComment(@RequestBody Comment comment) {
        service.addComment(comment);
    }

    @PutMapping("update")
    public void updateComment(@RequestBody Comment comment) {
        service.updateComment(comment);
    }

    @DeleteMapping("delete-{id}")
    public void deleteCommentById(@PathVariable Long id) {
        service.deleteCommentById(id);
    }

    @GetMapping("find-by-author-{authorNickname}")
    public List<Comment> findAllByAuthorNickname(@PathVariable String authorNickname) {
        return service.findAllByAuthorNickname(authorNickname);
    }


}
