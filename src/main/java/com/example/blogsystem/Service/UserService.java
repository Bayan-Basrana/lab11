package com.example.blogsystem.Service;

import com.example.blogsystem.ApiResponse.ApiException;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUser (){
        return userRepository.findAll();
    }

    public void add (User user ){
        user.setRegistrationDate(LocalDate.now());
        userRepository.save(user);
    }


    public void update (Integer userId ,User user){
        User old = userRepository.findUserByUserId(userId);
        if (old==null){
            throw new ApiException("User ID not found");
        }
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setEmail(user.getEmail());
        userRepository.save(old);
    }



    public void delete (Integer userId){
        User user = userRepository.findUserByUserId(userId);
        if (user==null){
            throw new ApiException("User ID not found");
        }
        userRepository.delete(user);
    }


//#1
    public User userByEmail (String email) {
        User user = userRepository.findUserByEmail(email);
        if (user==null){
            throw new ApiException("Email not found");
        }
        return user;
    }
//#2
    public List<User> getAllUsersBeforeRegistrationDate (LocalDate registrationDate){
        List<User> usersBeforeRegistrationDate = userRepository.getAllUsersBeforeRegistrationDate(registrationDate);
        if (usersBeforeRegistrationDate ==null){
            throw new ApiException("there is no user before this date");
        }
        return usersBeforeRegistrationDate;
    }


}
