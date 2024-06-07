package com.org.springboot.blog.service.impl;

import com.org.springboot.blog.Payload.CommentDto;
import com.org.springboot.blog.entity.Comments;
import com.org.springboot.blog.entity.Post;
import com.org.springboot.blog.exception.PostNotFoundException;
import com.org.springboot.blog.repository.CommentRepository;
import com.org.springboot.blog.repository.PostRepository;
import com.org.springboot.blog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, int postId) {
        Comments comments = mapToComment(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        comments.setPost(post);
        Comments newComment = commentRepository.save(comments);
        return mapToCommentDto(newComment);

    }

    @Override
    public List<CommentDto> getPostCommentsById(int postId) {
        List<Comments> comments = commentRepository.findPostCommentsByPostId(postId);
        return Optional.ofNullable(comments)
                .filter(c -> !c.isEmpty())
                .orElseThrow(() -> new PostNotFoundException("Post not found with id : " + postId, "The post you are looking for does not exist"))
                .stream()
                .map(this::mapToCommentDto)
                .toList();
    }

    public CommentDto mapToCommentDto(Comments comments) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comments.getId());
        commentDto.setName(comments.getName());
        commentDto.setEmail(comments.getEmail());
        commentDto.setMessageBody(comments.getMessageBody());
        return commentDto;
    }

    public Comments mapToComment(CommentDto commentDto) {
        Comments comments = new Comments();
        comments.setId(commentDto.getId());
        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setMessageBody(commentDto.getMessageBody());
        return comments;
    }
}
