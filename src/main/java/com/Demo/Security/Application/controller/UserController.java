package com.Demo.Security.Application.controller;

import com.Demo.Security.Application.entity.UserEntity;
import com.Demo.Security.Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserEntity user){
        try{
            UserEntity saveUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered Successfully");
        }
        catch (Exception e){
            throw new RuntimeException("Failed to create user");
        }
    }

}
