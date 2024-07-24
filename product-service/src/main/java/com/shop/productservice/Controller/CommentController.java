package com.shop.productservice.Controller;

import com.shop.productservice.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;



















}
