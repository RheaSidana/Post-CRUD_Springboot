package com.tw.crud.comment.service;

import com.tw.crud.comment.exception.CreateCommentException;
import com.tw.crud.comment.exception.DeleteCommentException;
import com.tw.crud.comment.exception.ReadCommentException;
import com.tw.crud.comment.exception.UpdateCommentException;
import com.tw.crud.comment.repository.CommentRepository;
import com.tw.crud.comment.repository.model.Comment;
import com.tw.crud.comment.view.model.CommentRequest;
import com.tw.crud.post.exception.ReadPostException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {
    private static long TEST_ID = 10;
    private CommentRepository commentRepository;
    private CommentRequest commentRequest;
    private CommentService commentService;
    private Comment comment;

    @BeforeEach
    void beforeEach() {
        commentRepository = mock(CommentRepository.class);
        commentService = new CommentService(commentRepository);
        comment = new Comment(2L, "Author", "Author@3", "Comment");
        commentRequest = new CommentRequest(comment.getPost_id(), comment.getAuthor_name(), comment.getAuthor_username(), comment.getContent());
    }

    @AfterEach
    void afterEach() {
        commentRepository.deleteAll();
    }

    @Test
    void shouldNotCreateCommentWhenPostIdIsInvalid() {
        commentRequest = new CommentRequest(0L, "Author", "Author@3", "Comment");
        String actualMessage = "Invalid Post Id";

        CreateCommentException createCommentException = assertThrows(CreateCommentException.class, () -> commentService.createComment(commentRequest));

        assertEquals(createCommentException.getMessage(), actualMessage);
    }

    @Test
    void shouldNotCreateCommentWhenFieldIsEmpty() {
        commentRequest = new CommentRequest(2L, "", "Author@3", "Comment");
        String actualMessage = "Name cannot be empty string";

        CreateCommentException createCommentException = assertThrows(CreateCommentException.class, () -> commentService.createComment(commentRequest));

        assertEquals(createCommentException.getMessage(), actualMessage);
    }

    @Test
    void shouldCreateComment() throws CreateCommentException, ReadPostException {
        Comment mockComment = mock(Comment.class);
        when(commentRepository.save(comment)).thenReturn(mockComment);
        when(commentRepository.findById(TEST_ID)).thenReturn(Optional.of(mockComment));

        commentService.createComment(commentRequest);

        verify(commentRepository).save(comment);
        assertEquals(commentRepository.findById(TEST_ID).get(),mockComment);
    }

    @Test
    void shouldNotReadCommentsWhenPostIdIsInvalid() {
        long post_Id = -1;
        String actualMessage = "Id should not be less than or equal to zero";

        ReadCommentException readCommentException = assertThrows(ReadCommentException.class, ()-> commentService.readComment(post_Id));
        assertEquals(readCommentException.getMessage(),actualMessage);
    }

    @Test
    void shouldReadComment() throws ReadPostException, CreateCommentException, ReadCommentException {
        List<Comment> mockCommentList = mock(List.class);
        when(commentRepository.findAllByPostId(TEST_ID)).thenReturn(mockCommentList);

        List<Comment> commentList = commentService.readComment(TEST_ID);

        assertEquals(commentList,mockCommentList);
    }

    @Test
    void shouldNotUpdateWhenPostNotInDB() {
        String actualMessage = "Comment Not Found";

        UpdateCommentException updateCommentException = assertThrows(UpdateCommentException.class, ()-> commentService.updateComment(TEST_ID,commentRequest));

        assertEquals(updateCommentException.getMessage(),actualMessage);
    }

    @Test
    void shouldUpdateComment() throws UpdateCommentException {
        long commentId = comment.getPost_id();
        Comment mockComment = mock(Comment.class);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(mockComment));
        when(commentRepository.save(mockComment)).thenReturn(mockComment);

        commentService.updateComment(commentId,commentRequest);

        verify(commentRepository).save(mockComment);
        assertEquals(commentRepository.findById(commentId).get(),mockComment);
    }

    @Test
    void shouldDeleteComment() throws DeleteCommentException {
        long commentId = comment.getPost_id();
        Comment mockComment = mock(Comment.class);
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(mockComment));

        commentService.deleteComment(commentId);

        verify(commentRepository).delete(mockComment);
    }
}