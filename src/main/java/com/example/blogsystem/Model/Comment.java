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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @NotEmpty(message = "content is empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "comment Date must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate commentDate;

    @Column(columnDefinition = "int not null")
    private Integer postId;
    @Column(columnDefinition = "int not null")
    private Integer userId;

    public Comment(Integer commentId, String content, LocalDate commentDate, Integer postId, Integer userId) {
        this.commentId = commentId;
        this.content = content;
        this.commentDate = commentDate;
        this.postId = postId;
        this.userId = userId;
    }

    public Comment() {

    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public void setContent(@NotEmpty(message = "content is empty") String content) {
        this.content = content;
    }

    public void setCommentDate(@FutureOrPresent(message = "comment Date must be Future Or Present") LocalDate commentDate) {
        this.commentDate = commentDate;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public @NotEmpty(message = "content is empty") String getContent() {
        return content;
    }

    public @FutureOrPresent(message = "comment Date must be Future Or Present") LocalDate getCommentDate() {
        return commentDate;
    }

    public Integer getPostId() {
        return postId;
    }

    public Integer getUserId() {
        return userId;
    }
}
