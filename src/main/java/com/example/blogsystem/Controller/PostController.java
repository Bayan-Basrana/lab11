package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    @GetMapping("/get")
    public ResponseEntity getPost (){
        return ResponseEntity.status(200).body(postService.getPost());
    }


    @PostMapping("/add")
    public ResponseEntity addPost (@RequestBody @Valid Post post , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
postService.add(post);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));

    }

    @PutMapping("/update/{postId}")
    public ResponseEntity update (@PathVariable Integer postId , @RequestBody @Valid Post post , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.update(postId,post);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity delete (@PathVariable Integer postId){
postService.delete(postId);
return ResponseEntity.status(200).body("deleted successfully");

    }
//#3

    @GetMapping("/postByUserId/{userId}")
public ResponseEntity postByUserId (@PathVariable Integer userId){
    List<Post> postByUserId = postService.postByUserId(userId);
    return ResponseEntity.status(200).body(postByUserId);

}

//#4

    @GetMapping("/postByPublishDate/{publishDate}")
    public ResponseEntity postByPublishDate (@PathVariable LocalDate publishDate){
        List<Post> postByPublishDate = postService.getAllByPublish_dateAfter(publishDate);
        return ResponseEntity.status(200).body(postByPublishDate);

    }
    //#5

    @GetMapping("/postByCategoryId/{categoryId}")
    public ResponseEntity findPostByCategoryId (@PathVariable Integer categoryId){
        List<Post> postByCategoryId = postService.findPostByCategoryId(categoryId);
        return ResponseEntity.status(200).body(postByCategoryId);

    }

}
