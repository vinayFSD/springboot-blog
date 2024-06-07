package com.org.springboot.blog.controller;

import com.org.springboot.blog.Payload.PostDto;
import com.org.springboot.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts/")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<PostDto> deletePost(@PathVariable int id) {
        return new ResponseEntity<>(postService.deletePost(id), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<List<PostDto>> getPostByTitle(@PathVariable String title) {
        return new ResponseEntity<>(postService.getPostByTitle(title), HttpStatus.OK);
    }

    @GetMapping("{id}/{title}")
    public ResponseEntity<PostDto> findByIdAndTitle(@PathVariable int id, @PathVariable String title) {
        return new ResponseEntity<>(postService.findByIdAndTitle(id, title), HttpStatus.OK);
    }

    @GetMapping("pagination/{pageNo}/{pageSize}/{fieldName}")
    public ResponseEntity<List<PostDto>> getAllPostsWithPagination(@PathVariable int pageNo, @PathVariable int pageSize, @PathVariable String fieldName) {
        return new ResponseEntity<>(postService.getAllPostsWithPagination(pageNo, pageSize, fieldName), HttpStatus.OK);
    }


}
