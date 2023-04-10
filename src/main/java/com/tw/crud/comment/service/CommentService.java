package com.tw.crud.comment.service;

import com.tw.crud.comment.exception.CreateCommentException;
import com.tw.crud.comment.exception.DeleteCommentException;
import com.tw.crud.comment.exception.ReadCommentException;
import com.tw.crud.comment.exception.UpdateCommentException;
import com.tw.crud.comment.repository.CommentRepository;
import com.tw.crud.comment.repository.model.Comment;
import com.tw.crud.comment.view.model.CommentRequest;
import com.tw.crud.post.exception.ReadPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public void createComment(CommentRequest commentRequest) throws CreateCommentException, ReadPostException {
        if (commentRequest.getPost_id() <= 0) {
            throw new CreateCommentException("Invalid Post Id");
        }
        if (commentRequest.getAuthor_username().equals("")) {
            throw new CreateCommentException("Username cannot be empty string");
        }
        if (commentRequest.getAuthor_name().equals("")) {
            throw new CreateCommentException("Name cannot be empty string");
        }
        if (commentRequest.getComment().equals("")) {
            throw new CreateCommentException("Comment cannot be empty string");
        }

        Comment comment = new Comment(commentRequest.getPost_id(),
                commentRequest.getAuthor_name(),
                commentRequest.getAuthor_username(),
                commentRequest.getComment());

        Comment commentCreated = commentRepository.save(comment);
    }

    public List<Comment> readComment(long postId) throws ReadCommentException {
        if (postId <= 0) {
            throw new ReadCommentException("Id should not be less than or equal to zero");
        }

        List<Comment> comments = commentRepository.findAllByPostId(postId);

        return comments;
    }

    public void updateComment(long id, CommentRequest commentRequest) throws UpdateCommentException {
        if (id <= 0) {
            throw new UpdateCommentException("Invalid Id");
        }

        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new UpdateCommentException("Comment Not Found");
        }

        Comment commentToUpdate = comment.get();
        commentToUpdate.setContent(commentRequest.getComment());
        commentToUpdate.setDate(Date.valueOf(LocalDate.now()));
        commentToUpdate.setTime(Time.valueOf(LocalTime.now()));

        commentRepository.save(commentToUpdate);
    }

    public void deleteComment(long id) throws DeleteCommentException {
        if (id <= 0){
            throw new DeleteCommentException("Invalid Id");
        }

        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isEmpty()){
            throw new DeleteCommentException("Comment Not Found");
        }

        Comment commentToDelete = comment.get();

        commentRepository.delete(commentToDelete);
    }
}
