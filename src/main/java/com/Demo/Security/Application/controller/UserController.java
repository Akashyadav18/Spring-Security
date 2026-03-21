package com.Demo.Security.Application.controller;

import com.Demo.Security.Application.entity.UserEntity;
import com.Demo.Security.Application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserEntity user){
        try{
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered Successfully");
        }
        catch (Exception e){
            throw new RuntimeException("Failed to create user");
        }
    }

    // This method is not suitable for frontend, it is just for testing
//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(Authentication authentication){
//        try{
//            return ResponseEntity.ok("Welcome "+authentication.getName());
//        }
//        catch (Exception e){
//            throw new RuntimeException("Failed to login");
//        }
//    }

    // This method is suitable for frontend, u can pass data through body.
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid UserEntity user){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword())
            );
            return ResponseEntity.ok("Welcome " + authentication.getName());
        }
        catch (Exception e){
            throw new RuntimeException("Failed to login");
        }
    }

//    @GetMapping("/csrf")
//    public CsrfToken getCsrfToken(HttpServletRequest request){
//        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
//    }

}
