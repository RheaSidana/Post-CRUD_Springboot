package com.tw.crud.comment.view.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CommentRequest {
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Long post_id;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String author_name;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String author_username;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String comment;

    public CommentRequest() {
    }

    public CommentRequest(Long post_id, String author_name, String author_username, String comment) {
        this.post_id = post_id;
        this.author_name = author_name;
        this.author_username = author_username;
        this.comment = comment;
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentRequest that = (CommentRequest) o;
        return Objects.equals(post_id, that.post_id) &&
                Objects.equals(author_name, that.author_name) &&
                Objects.equals(author_username, that.author_username) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(post_id, author_name, author_username, comment);
    }

    @Override
    public String toString() {
        return "CommentRequest{" +
                "post_id=" + post_id +
                ", author_name='" + author_name + '\'' +
                ", author_username='" + author_username + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
