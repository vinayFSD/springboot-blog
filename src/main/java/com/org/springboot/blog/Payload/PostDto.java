package com.org.springboot.blog.Payload;

import lombok.Data;

@Data
public class PostDto {
    private int id;
    private String title;
    private String description;
    private String content;
}
