package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.Post;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.CategoryRepository;
import com.example.blogsystem.Repository.PostRepository;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class PostService {
    private final PostRepository postRepository;
private final UserRepository userRepository;
private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }




    public List<Post> getPost (){
        return postRepository.findAll();
    }

    public void add (Post post ){
if(userRepository.findUserByUserId(post.getUserId())==null ){
    throw new ApiException("user ID Not found");
}
if (categoryRepository.findCategoriesByCategoryId(post.getCategoryId())==null){
    throw new ApiException("CategoryId not found ");
}
post.setPublish_date(LocalDate.now());
        postRepository.save(post);
    }


    public void update (Integer postId ,Post post){
        Post old = postRepository.findPostByPostId(postId);
        if (old==null){
            throw new ApiException("User ID not found");
        }
        if(userRepository.findUserByUserId(post.getUserId())==null ){
            throw new ApiException("user ID Not found");
        }
        if (categoryRepository.findCategoriesByCategoryId(post.getCategoryId())==null){
            throw new ApiException("Category Id not found ");
        }


        old.setTitle(post.getTitle());
        old.setContent(post.getContent());
        postRepository.save(old);
    }



    public void delete (Integer postId){
        Post post = postRepository.findPostByPostId(postId);
        if (post==null){
            throw new ApiException("post ID not found");
        }
        postRepository.delete(post);
    }
//#3
    public List<Post> postByUserId (Integer userId) {
        List<Post> postByUserId = postRepository.getPostByUserId(userId);
        if (postByUserId==null){
            throw new ApiException("user Id not found");
        }
        return postByUserId;
    }
    //#4
    public List<Post> getAllByPublish_dateAfter (LocalDate publishDate) {
        List<Post> postByPublishDate = postRepository.getAllByPublish_dateAfter(publishDate);
        if (postByPublishDate==null){
            throw new ApiException("no post after this date");
        }
        return postByPublishDate;

    }

//#5
    public List<Post> findPostByCategoryId (Integer categoryId){
        List<Post> postByCategoryId = postRepository.findPostByCategoryId(categoryId);
  if (postByCategoryId==null){
      throw new ApiException("no post by this category");
  }
  return postByCategoryId;
    }

}
