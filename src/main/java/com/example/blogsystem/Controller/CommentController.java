package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Comment;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
public class CommentController {
    private  final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    @GetMapping("/get")
    public ResponseEntity getComment (){
        return ResponseEntity.status(200).body(commentService.getComment());
    }


    @PostMapping("/add")
    public ResponseEntity addComment (@RequestBody @Valid Comment comment , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.add(comment);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));

    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity update (@PathVariable Integer commentId , @RequestBody @Valid Comment comment , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.update(commentId,comment);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity delete (@PathVariable Integer commentId){
        commentService.delete(commentId);
        return ResponseEntity.status(200).body("deleted successfully");

    }
//#6
    @GetMapping("/commentByPostId/{postId}")
    public ResponseEntity commentByPostId (@PathVariable Integer postId){
        List<Comment> commentByPostId = commentService.commentByPostId(postId);
        return ResponseEntity.status(200).body(commentByPostId);

    }
//#7
    @GetMapping("/commentByUserIdOrderByCommentDate/{userId}")
    public ResponseEntity commentByUserIdOrderByCommentDate (@PathVariable Integer userId){
        List<Comment> commentByUserIdOrderByCommentDate = commentService.findCommentByUserIdOrderByCommentDate(userId);
        return ResponseEntity.status(200).body(commentByUserIdOrderByCommentDate);

    }

//#8
    @GetMapping("/byPostIdOrderByCommentDate/{postId}")
    public ResponseEntity findByPostIdOrderByCommentDate (@PathVariable Integer postId ){
        List<Comment> byPostIdOrderByCommentDate = commentService.findByPostIdOrderByCommentDate(postId);
        return ResponseEntity.status(200).body(byPostIdOrderByCommentDate);

    }



}
