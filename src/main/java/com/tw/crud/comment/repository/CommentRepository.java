package com.tw.crud.comment.repository;

import com.tw.crud.comment.repository.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(
            value = "Select * from Comments where post_id= ?1", nativeQuery = true
    )
    List<Comment> findAllByPostId(long postId);
}
