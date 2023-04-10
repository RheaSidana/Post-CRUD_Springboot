package com.tw.crud.post.repository.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Column(name = "Author_Username", unique = true)
    @NotNull(message = "Author username cannot be null")
    @JsonProperty
    private String author_username;

    @Column(name = "Author_Name")
    @NotNull(message = "Author name cannot be null")
    @JsonProperty
    private String author_name;

    @Column(name = "Content")
    @NotNull(message = "content cannot be null")
    @JsonProperty
    private String content;

    @Column(name = "Date")
    @CreatedDate
    @NotNull(message = "date cannot be null")
    @JsonProperty
    private Date date;

    @Column(name = "Time")
    @CreationTimestamp
    @NotNull(message = "Time cannot be null")
    @JsonProperty
    private Time time;

    @Column(name = "Audience_Type")
    @NotNull(message = "audience type cannot be null")
    @JsonProperty
    private String audience_type;

    public Post() {
    }

    public Post(String author_username,
                String authorName,
                String content,
                String audience_Type) {
        this.author_username = author_username;
        this.author_name = authorName;
        this.content = content;
        this.date = Date.valueOf(LocalDate.now());
        this.time = Time.valueOf(LocalTime.now());
        this.audience_type = audience_Type;
    }

    public Long getId() {
        return id;
    }

    public String getAuthor_username() {
        return author_username;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getAudience_type() {
        return audience_type;
    }

    public void setAuthor_username(String author_username) {
        this.author_username = author_username;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setAudience_type(String audience_type) {
        this.audience_type = audience_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(author_username, post.author_username) && Objects.equals(author_name, post.author_name) && Objects.equals(content, post.content) && Objects.equals(date, post.date) && Objects.equals(time, post.time) && Objects.equals(audience_type, post.audience_type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author_username, author_name, content, date, time, audience_type);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", authorUsername='" + author_username + '\'' +
                ", authorName='" + author_name + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", audience_Type='" + audience_type + '\'' +
                '}';
    }
}
