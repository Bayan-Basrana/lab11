package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Repository.CommentRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
private  final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }


    public List<Comment> getComment (){
        return commentRepository.findAll();
    }


    public void add (Comment comment ){
        if(userRepository.findUserByUserId(comment.getUserId())==null ){
            throw new ApiException("user ID Not found");
        }
        if (postRepository.findPostByPostId(comment.getPostId())==null){
            throw new ApiException("post Id not found ");
        }
        comment.setCommentDate(LocalDate.now());
        commentRepository.save(comment);
    }


    public void update (Integer commentId ,Comment comment){
        Comment old = commentRepository.findCommentByCommentId(commentId);
        if (old==null){
            throw new ApiException("comment ID not found");
        }
        if(userRepository.findUserByUserId(comment.getUserId())==null ){
            throw new ApiException("user ID Not found");
        }
        if (postRepository.findPostByPostId(comment.getPostId())==null){
            throw new ApiException("post Id not found ");
        }


        old.setContent(comment.getContent());
        commentRepository.save(old);
    }



    public void delete (Integer commentId){
        Comment comment = commentRepository.findCommentByCommentId(commentId);
        if (comment==null){
            throw new ApiException("comment ID not found");
        }
        commentRepository.delete(comment);
    }
//#6
    public List<Comment> commentByPostId (Integer postId){
        List<Comment> commentByPostId = commentRepository.getCommentByPostId(postId);

        if (commentByPostId==null){
            throw new ApiException("post ID not found");
        }
        return commentByPostId;
    }
//#7
    public List<Comment> findCommentByUserIdOrderByCommentDate (Integer userId ){
        List<Comment> commentByUserIdOrderByCommentDate = commentRepository.findCommentByUserIdOrderByCommentDate(userId);

        if (commentByUserIdOrderByCommentDate==null){
            throw new ApiException(" not found");
        }
        return commentByUserIdOrderByCommentDate;
    }
//#8
    public List<Comment> findByPostIdOrderByCommentDate (Integer postId ){
        List<Comment> byPostIdOrderByCommentDate = commentRepository.findByPostIdOrderByCommentDate(postId);

        if (byPostIdOrderByCommentDate==null){
            throw new ApiException(" not found");
        }
        return byPostIdOrderByCommentDate;
    }





}
