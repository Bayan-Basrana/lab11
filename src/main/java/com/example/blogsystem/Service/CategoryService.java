package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Category;
import com.example.blogsystem.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategory (){
        return categoryRepository.findAll();
    }

    public void add (Category category){
        categoryRepository.save(category);
    }


    public void update (Integer categoryId ,Category category){
        Category old = categoryRepository.findCategoriesByCategoryId(categoryId);

        if (old==null){
            throw new ApiException("categoryId not found");
        }

        old.setName(category.getName());
        categoryRepository.save(old);
    }


    public void delete (Integer categoryId){
        Category category = categoryRepository.findCategoriesByCategoryId(categoryId);
        if (category==null){
            throw new ApiException("categoryId not found");
        }

        categoryRepository.delete(category);
    }

}
