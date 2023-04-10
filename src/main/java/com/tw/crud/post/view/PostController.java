package com.tw.crud.post.view;

import com.tw.crud.post.exception.CreatePostException;
import com.tw.crud.post.exception.ReadPostException;
import com.tw.crud.post.exception.UpdatePostException;
import com.tw.crud.post.repository.model.Post;
import com.tw.crud.post.service.PostService;
import com.tw.crud.post.view.model.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/createPost")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createPost(@RequestBody PostRequest postRequest) throws CreatePostException {
        postService.createPost(postRequest);
        return "Post is created";
    }

    @GetMapping(path = "/readPost")
    public Object readPost(@RequestParam(name = "id")  long id) throws ReadPostException {
        Post post;
        try {
            post = postService.readPost(id);
        }
        catch (ReadPostException ex){
            return ex.getMessage();
        }
        return post;
    }

    @PutMapping(path = "/updatePost")
    public String updatePost(@RequestParam(name = "id") long id, @RequestBody PostRequest postRequest) throws UpdatePostException, ReadPostException {
        postService.updatePost(id,postRequest);
        return "Post is updated";
    }

    @DeleteMapping(path = "deletePost")
    public String deletePost(@RequestParam(name = "id") long id) throws ReadPostException {
        postService.deletePost(id);
        return "Post is deleted";
    }
}
