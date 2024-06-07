package com.org.springboot.blog.exception;

import org.springframework.http.HttpStatus;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Post not found excep")
public class PostNotFoundException extends RuntimeException {
    private String customMessage;

    public PostNotFoundException(String message, String customMessage) {
        super(message);
        this.customMessage = customMessage;
    }

}



