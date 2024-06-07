package com.org.springboot.blog.service.impl;

import com.org.springboot.blog.Payload.PostDto;
import com.org.springboot.blog.entity.Post;
import com.org.springboot.blog.repository.PostRepository;
import com.org.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post newPost = postRepository.save(post);
        return mapToPostDto(newPost);
    }

    @Override
    public PostDto deletePost(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            PostDto postDto = mapToPostDto(post);
            postRepository.deleteById(id);
            return postDto;
        } else {
            throw new IllegalArgumentException("Post with id " + id + " not found");
        }
    }

    @Override
    public PostDto getPostById(int id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            return mapToPostDto(post);
        } else {
            throw new IllegalArgumentException("Post with id " + id + " not found");
        }
    }

    @Override
    public List<PostDto> getPostByTitle(String title) {
        List<Post> posts = postRepository.findByTitle(title);
        return posts.stream().map(this::mapToPostDto).toList();
    }

    @Override
    public PostDto findByIdAndTitle(int id, String title) {
        Post post = postRepository.findByIdAndTitle(id, title);
        if (post == null)
            throw new IllegalArgumentException("Post with id " + id + " and title " + title + " not found");
        return mapToPostDto(post);
    }

    @Override
    public List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize, String fieldName) {
        List<Post> posts = postRepository.findAll(PageRequest.of(pageNo, pageSize, Sort.by(fieldName).descending())).stream().toList();
        return posts.stream().map(this::mapToPostDto).toList();
    }

    private PostDto mapToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
