package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment ,Integer> {

    Comment findCommentByCommentId (Integer commentId);
     //#6
    @Query("select c from Comment c where c.postId=?1")
    List<Comment> getCommentByPostId (Integer postId);
    //#7
    List<Comment> findCommentByUserIdOrderByCommentDate (Integer userId);
    //#8
    List<Comment> findByPostIdOrderByCommentDate (Integer postId );


}
