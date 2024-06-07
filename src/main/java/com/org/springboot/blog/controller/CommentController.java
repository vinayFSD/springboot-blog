package com.org.springboot.blog.controller;

import com.org.springboot.blog.Payload.CommentDto;
import com.org.springboot.blog.service.CommentService;
import com.org.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("posts/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable int postId) {
        return new ResponseEntity<>(commentService.createComment(commentDto, postId), HttpStatus.CREATED);
    }

    @GetMapping("{postId}")
    public ResponseEntity<List<CommentDto>> getPostCommentsById(@PathVariable int postId) {
        return new ResponseEntity<>(commentService.getPostCommentsById(postId), HttpStatus.OK);
    }

}
