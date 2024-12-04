package com.example.blogsystem.Controller;

import com.example.blogsystem.ApiResponse.ApiResponse;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get")
    public ResponseEntity getUser (){
        return ResponseEntity.status(200).body(userService.getUser());
    }


    @PostMapping("/add")
    public ResponseEntity addUser (@RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.add(user);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity update (@PathVariable Integer userId , @RequestBody @Valid User user , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
             userService.update(userId,user);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity delete (@PathVariable Integer userId){
        userService.delete(userId);
        return ResponseEntity.status(200).body("deleted successfully");

    }

    //#1
@GetMapping("/byEmail/{email}")
public ResponseEntity userByEmail (@PathVariable String  email){
        User user = userService.userByEmail(email);
       return ResponseEntity.status(200).body(user) ;
}
//#2
@GetMapping("/beforeRegistrationDate/{registrationDate}")
public ResponseEntity usersBeforeRegistrationDate (@PathVariable LocalDate registrationDate){
    List<User> usersBeforeRegistrationDate =userService.getAllUsersBeforeRegistrationDate(registrationDate);
    return ResponseEntity.status(200).body(usersBeforeRegistrationDate) ;

}

}
