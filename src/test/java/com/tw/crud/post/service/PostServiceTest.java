package com.tw.crud.post.service;

import com.tw.crud.post.exception.CreatePostException;
import com.tw.crud.post.exception.ReadPostException;
import com.tw.crud.post.exception.UpdatePostException;
import com.tw.crud.post.repository.PostRepository;
import com.tw.crud.post.repository.model.Post;
import com.tw.crud.post.view.model.PostRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {
    private PostRepository postRepository;
    private PostService postService;
    private Post post;
    private PostRequest postRequest;
    private static long TEST_ID = 10;

    @BeforeEach
    public void beforeEach() {
        postRepository = mock(PostRepository.class);
        postService = new PostService(postRepository);
        post = new Post("Authonr@3", "Author", "...Content...", "public");
        postRequest = new PostRequest(post.getAuthor_username(), post.getAuthor_name(), post.getContent(), post.getAudience_type());
    }

    @AfterEach
    public void afterEach() {
        postRepository.deleteAll();
    }

    @Test
    void shouldNotCreatePostWhenAuthorNameIsEmptyString() {
        postRequest.setAuthorName("");
        String actualMessage = "Author Name is empty.";

        CreatePostException createPostException = assertThrows(CreatePostException.class, () -> postService.createPost(postRequest));
        assertEquals(createPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldNotCreatePostWhenAuthorUsernameIsEmptyString() {
        postRequest.setAuthorUsername("");
        String actualMessage = "Author Username is empty.";

        CreatePostException createPostException = assertThrows(CreatePostException.class, () -> postService.createPost(postRequest));
        assertEquals(createPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldNotCreatePostWhenContentIsEmptyString() {
        postRequest.setContent("");
        String actualMessage = "Content is empty.";

        CreatePostException createPostException = assertThrows(CreatePostException.class, () -> postService.createPost(postRequest));
        assertEquals(createPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldNotCreatePostWhenAudienceTypeIsEmptyString() {
        postRequest.setAudienceType("");
        String actualMessage = "Audience type is empty.";

        CreatePostException createPostException = assertThrows(CreatePostException.class, () -> postService.createPost(postRequest));
        assertEquals(createPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldCreatePost() throws CreatePostException {
        Post mockPost = mock(Post.class);
        when(postRepository.save(post)).thenReturn(mockPost);
        when(postRepository.findById(TEST_ID)).thenReturn(Optional.of(mockPost));

        postService.createPost(postRequest);

        verify(postRepository).save(post);
        assertEquals(postRepository.findById(TEST_ID).get(), mockPost);
    }

    @Test
    void shouldThrowExceptionWhenIdIsLessThanOrEqualToZero() {
        String actualMessage = "Id should not be less than or equal to zero";

        ReadPostException readPostException = assertThrows(ReadPostException.class, () -> postService.readPost(0));
        assertEquals(readPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldThrowExceptionWhenIdIsNotPresentInDB() {
        String actualMessage = "Post not found";

        ReadPostException readPostException = assertThrows(ReadPostException.class, () -> postService.readPost(3));
        assertEquals(readPostException.getMessage(), actualMessage);
    }

    @Test
    void shouldReadPost() throws ReadPostException {
        Post mockPost = mock(Post.class);
        when(postRepository.save(post)).thenReturn(mockPost);
        when(postRepository.findById(TEST_ID)).thenReturn(Optional.of(mockPost));

        Post expectedPost = postService.readPost(TEST_ID);

        assertEquals(expectedPost, mockPost);
    }

    @Test
    void shouldUpdatePost() throws CreatePostException, ReadPostException, UpdatePostException {
        Post mockPost = mock(Post.class);
        when(postRepository.save(post)).thenReturn(mockPost);
        when(postRepository.findById(TEST_ID)).thenReturn(Optional.of(mockPost));
        postService.createPost(postRequest);
        post.setAuthor_name("My Author");
        PostRequest postRequestUpdated = new PostRequest(post.getAuthor_username(), post.getAuthor_name(), post.getContent(), post.getAudience_type());
        when(postRepository.save(post)).thenReturn(mockPost);

        postService.updatePost(TEST_ID, postRequestUpdated);

        assertEquals(postRepository.findById(TEST_ID).get(), mockPost);
    }

    @Test
    void shouldThrowExceptionWhenPostIsNotPresentInDB() {
        assertThrows(ReadPostException.class, () -> postService.deletePost(TEST_ID));
    }

    @Test
    void shouldDeletePost() throws CreatePostException, ReadPostException {
        Post mockPost = mock(Post.class);
        when(postRepository.save(post)).thenReturn(mockPost);
        when(postRepository.findById(TEST_ID)).thenReturn(Optional.of(mockPost));
        postService.createPost(postRequest);

        postService.deletePost(TEST_ID);

        verify(postRepository).delete(mockPost);
    }
}