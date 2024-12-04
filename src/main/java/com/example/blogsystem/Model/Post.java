package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer postId;
    @NotEmpty(message = "title is empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String title;
    @NotEmpty(message = "content is empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "publish_date must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate publishDate;
    @Column(columnDefinition = "int not null")
    private Integer categoryId;
    @Column(columnDefinition = "int not null")
    private Integer userId;

    public Post() {

    }


    public Post(Integer postId, String title, String content, LocalDate publish_date, Integer categoryId, Integer userId) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.publishDate = publish_date;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public @NotEmpty(message = "title is empty") String getTitle() {
        return title;
    }

    public @NotEmpty(message = "content is empty") String getContent() {
        return content;
    }

    public @FutureOrPresent(message = "publish_date must be Future Or Present") LocalDate getPublish_date() {
        return publishDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setTitle(@NotEmpty(message = "title is empty") String title) {
        this.title = title;
    }

    public void setContent(@NotEmpty(message = "content is empty") String content) {
        this.content = content;
    }

    public void setPublish_date(@FutureOrPresent(message = "publish_date must be Future Or Present") LocalDate publish_date) {
        this.publishDate = publish_date;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
