package com.org.springboot.blog.repository;

import com.org.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer>{
List<Post> findByTitle(String title);
@Query("SELECT p FROM Post p WHERE p.id = ?1 AND p.title = ?2")
Post findByIdAndTitle(int id, String title);
}

