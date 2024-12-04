package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post ,Integer> {

    Post findPostByPostId (Integer postId);

//#3
    @Query("select p from Post p where p.userId=?1")
    List<Post> getPostByUserId (Integer userId);
//#4
@Query("select p from Post p where p.publishDate>?1")
    List<Post> getAllByPublish_dateAfter (LocalDate publishDate);

//#5
List<Post> findPostByCategoryId (Integer categoryId);

}
