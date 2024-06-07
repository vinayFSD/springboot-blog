package com.org.springboot.blog.service;

import com.org.springboot.blog.Payload.PostDto;

import java.util.List;

public interface PostService {
PostDto createPost(PostDto postDto);
PostDto deletePost(int id);
PostDto getPostById(int id);
List<PostDto> getPostByTitle(String title);
PostDto findByIdAndTitle(int id, String title);
List<PostDto> getAllPostsWithPagination(int pageNo, int pageSize,String fieldName);

}
