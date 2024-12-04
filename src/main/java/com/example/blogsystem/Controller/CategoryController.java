package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/category")

public class CategoryController {
    public CategoryService getCategoryService() {
        return categoryService;
    }

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/get")
    public ResponseEntity getCategory (){
        return ResponseEntity.status(200).body(categoryService.getCategory());
    }


    @PostMapping("/add")
    public ResponseEntity addCategory (@RequestBody @Valid Category category, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
categoryService.add(category);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));

    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity update (@PathVariable Integer categoryId , @RequestBody @Valid Category category , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
categoryService.update(categoryId,category);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity delete (@PathVariable Integer categoryId){
categoryService.delete(categoryId);
        return ResponseEntity.status(200).body("deleted successfully");

    }
}
