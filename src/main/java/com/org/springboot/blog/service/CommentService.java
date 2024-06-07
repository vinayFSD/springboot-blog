package com.org.springboot.blog.service;

import com.org.springboot.blog.Payload.CommentDto;
import com.org.springboot.blog.Payload.PostDto;

import java.util.List;

public interface CommentService{
    CommentDto createComment(CommentDto commentDto, int postId);
    List<CommentDto> getPostCommentsById(int postId);
}
