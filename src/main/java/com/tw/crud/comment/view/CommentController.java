package com.tw.crud.comment.view;

import com.tw.crud.comment.exception.CreateCommentException;
import com.tw.crud.comment.exception.DeleteCommentException;
import com.tw.crud.comment.exception.ReadCommentException;
import com.tw.crud.comment.exception.UpdateCommentException;
import com.tw.crud.comment.repository.model.Comment;
import com.tw.crud.comment.service.CommentService;
import com.tw.crud.comment.view.model.CommentRequest;
import com.tw.crud.post.exception.ReadPostException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/createComment")
    public void createCommentOnThePost(@Valid @RequestBody CommentRequest commentRequest) throws CreateCommentException, ReadPostException {
        commentService.createComment(commentRequest);
    }

    @GetMapping("/readComments")
    public String readCommentsOnThePost(@Valid @RequestParam(name = "postId") long postId) throws ReadCommentException {
        List<Comment> comments = commentService.readComment(postId);
        String commentsString = "";
        for (Comment comment: comments) {
            commentsString += comment.toString() + "\n";
        }
        return commentsString;
    }

    @PutMapping("/updateComment")
    public void updateCommentOnThePost(@Valid @RequestParam(name = "id") long id, @RequestBody CommentRequest commentRequest) throws UpdateCommentException {
        commentService.updateComment(id,commentRequest);
    }

    @DeleteMapping("/deleteComment")
    public void deleteCommentOnThePost(@Valid @RequestParam(name = "id") long id) throws DeleteCommentException {
        commentService.deleteComment(id);
    }
}
