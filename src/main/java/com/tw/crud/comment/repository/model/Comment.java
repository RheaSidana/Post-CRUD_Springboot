package com.tw.crud.comment.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "Comments")
public class Comment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty
    private Long id;

    @Column(name = "post_id", nullable = false)
    @NotNull(message = "Post Id cannot be null")
    @JsonProperty
    private Long post_id;

    @Column(name = "author_name", nullable = false)
    @NotNull(message = "Author name cannot be null")
    @JsonProperty
    private String author_name;

    @Column(name = "author_username", nullable = false)
    @NotNull(message = "Author Username cannot be null")
    @JsonProperty
    private String author_username;

    @Column(name = "comment", nullable = false)
    @NotNull(message = "Comment cannot be null")
    @JsonProperty
    private String content;

    @Column(name = "date", nullable = false)
    @NotNull(message = "message cannot be null")
    @JsonProperty
    private Date date;

    @Column(name = "time", nullable = false)
    @NotNull(message = "Time cannot be null")
    @JsonProperty
    private Time time;

    public Comment() {
    }

    public Long getPost_id() {
        return post_id;
    }

    public void setPost_id(Long post_id) {
        this.post_id = post_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Comment(Long post_id,
                   String author_name,
                   String author_username,
                   String content) {
        this.post_id = post_id;
        this.author_name = author_name;
        this.author_username = author_username;
        this.content = content;
        this.date = Date.valueOf(LocalDate.now());
        this.time = Time.valueOf(LocalTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) &&
                Objects.equals(post_id, comment.post_id) &&
                Objects.equals(author_name, comment.author_name) &&
                Objects.equals(author_username, comment.author_username) &&
                Objects.equals(content, comment.content) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(time, comment.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, post_id, author_name, author_username, content, date, time);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", author_name='" + author_name + '\'' +
                ", author_username='" + author_username + '\'' +
                ", commnent='" + content + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }
}
