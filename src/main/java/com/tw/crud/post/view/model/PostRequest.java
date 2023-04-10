package com.tw.crud.post.view.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PostRequest {
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String authorUsername;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String authorName;

    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String content;

    @JsonProperty
    private String audienceType;

    public PostRequest() {
    }

    public PostRequest(String authorUsername, String authorName, String content, String audienceType) {
        this.authorUsername = authorUsername;
        this.authorName = authorName;
        this.content = content;
        this.audienceType = audienceType;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAudienceType() {
        return audienceType;
    }

    public void setAudienceType(String audienceType) {
        this.audienceType = audienceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostRequest that = (PostRequest) o;
        return Objects.equals(authorUsername, that.authorUsername) &&
                Objects.equals(authorName, that.authorName) &&
                Objects.equals(content, that.content) &&
                Objects.equals(audienceType, that.audienceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorUsername, authorName, content, audienceType);
    }

    @Override
    public String toString() {
        return "{" +
                "authorUsername='" + authorUsername + '\'' +
                ", authorName='" + authorName + '\'' +
                ", content='" + content + '\'' +
                ", audienceType='" + audienceType + '\'' +
                '}';
    }
}
