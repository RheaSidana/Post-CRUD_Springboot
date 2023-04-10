package com.tw.crud.post.service;

import com.tw.crud.post.exception.CreatePostException;
import com.tw.crud.post.exception.ReadPostException;
import com.tw.crud.post.exception.UpdatePostException;
import com.tw.crud.post.repository.PostRepository;
import com.tw.crud.post.repository.model.Post;
import com.tw.crud.post.view.model.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostRequest postRequest) throws CreatePostException {
        if (postRequest.getAuthorName().equals("")){
            throw new CreatePostException("Author Name is empty.");
        }
        if(postRequest.getAuthorUsername().equals("")){
            throw new CreatePostException("Author Username is empty.");
        }
        if(postRequest.getContent().equals("")){
            throw new CreatePostException("Content is empty.");
        }
        if(postRequest.getAudienceType().equals("")) {
            throw new CreatePostException("Audience type is empty.");
        }

        Post post = new Post(postRequest.getAuthorUsername(), postRequest.getAuthorName(), postRequest.getContent(), postRequest.getAudienceType());
        Post post_create = postRepository.save(post);

    }

    public Post readPost(long id) throws ReadPostException {
        if(id<=0) {
            throw new ReadPostException("Id should not be less than or equal to zero");
        }

        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()) {
            throw new ReadPostException("Post not found");
        }

        Post searchedPost = post.get();
        return searchedPost;
    }

    public void updatePost(long id, PostRequest postRequest) throws UpdatePostException, ReadPostException {
        Post post = readPost(id);

        if (postRequest.getAuthorName().equals("")){
            throw new UpdatePostException("Author Name is empty.");
        }
        if(postRequest.getAuthorUsername().equals("")){
            throw new UpdatePostException("Author Username is empty.");
        }
        if(postRequest.getContent().equals("")){
            throw new UpdatePostException("Content is empty.");
        }
        if(postRequest.getAudienceType().equals("")) {
            throw new UpdatePostException("Audience type is empty.");
        }
//        if(post.getAuthor_name().equals(postRequest.getAuthorName()) &&
//                post.getAuthor_username().equals(postRequest.getAuthorUsername()) &&
//                post.getAudience_type().equals(postRequest.getAudienceType()) &&
//                post.getContent().equals(postRequest.getContent())){
//            throw new UpdatePostException("No changes applied");
//        }
//        System.out.println("Print: "+post);

        post.setAuthor_username(postRequest.getAuthorUsername());
        post.setAuthor_name(postRequest.getAuthorName());
        post.setContent(postRequest.getContent());
        post.setDate(Date.valueOf(LocalDate.now()));
        post.setTime(Time.valueOf(LocalTime.now()));
        post.setAudience_type(postRequest.getAudienceType());

//        System.out.println(post);

        postRepository.save(post);
    }

    public void deletePost(long id) throws ReadPostException {
        Post post = readPost(id);

        postRepository.delete(post);
    }
}
