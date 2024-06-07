package com.org.springboot.blog.repository;

import com.org.springboot.blog.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Integer> {
    @Query("SELECT c FROM Comments c WHERE c.post.id = ?1")
    List<Comments> findPostCommentsByPostId(int postId);
}
