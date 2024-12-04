package com.example.blogsystem.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @NotEmpty(message = "username is empty")
    @Size(min=5,message = "username must be more than 4")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String username ;
    @NotEmpty(message = "password is empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String password ;
    @NotEmpty(message = "email is empty")
    @Email(message = "must be valid email ")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String email ;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "registrationDate must be Future Or Present")
    @Column(columnDefinition = "datetime")
    private LocalDate registrationDate;


    public User() {

    }
    public User(Integer userId, String username, String password, String email, LocalDate registrationDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDate = registrationDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public @NotEmpty(message = "username is empty") @Size(min = 5, message = "username must be more than 4") String getUsername() {
        return username;
    }

    public @NotEmpty(message = "password is empty") String getPassword() {
        return password;
    }

    public @NotEmpty(message = "email is empty") @Email(message = "must be valid email ") String getEmail() {
        return email;
    }

    public @FutureOrPresent(message = "postingDate must be Future Or Present") LocalDate getRegistrationDate() {
        return registrationDate;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(@NotEmpty(message = "username is empty") @Size(min = 5, message = "username must be more than 4") String username) {
        this.username = username;
    }

    public void setPassword(@NotEmpty(message = "password is empty") String password) {
        this.password = password;
    }

    public void setEmail(@NotEmpty(message = "email is empty") @Email(message = "must be valid email ") String email) {
        this.email = email;
    }

    public void setRegistrationDate(@FutureOrPresent(message = "postingDate must be Future Or Present") LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
