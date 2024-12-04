package com.example.blogsystem.Repository;

import com.example.blogsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserId (Integer userId);
//#1
    User findUserByEmail (String  email);
//#2
    @Query("select u from User u where u.registrationDate<?1")
    List<User> getAllUsersBeforeRegistrationDate (LocalDate registrationDate);

}
