package com.org.springboot.blog.Payload;

import com.org.springboot.blog.entity.Post;
import lombok.Data;

@Data
public class CommentDto {
    private int id;
    private String name;
    private String email;
    private String messageBody;
//    private Post post;
}
