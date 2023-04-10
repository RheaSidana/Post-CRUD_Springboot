package com.tw.crud.post.repository;

import com.tw.crud.post.repository.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
