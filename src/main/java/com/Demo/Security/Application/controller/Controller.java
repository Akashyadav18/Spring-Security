package com.Demo.Security.Application.controller;

import com.Demo.Security.Application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/hi")
    public String hi() {
        return "hi World";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello World";
    }

    @GetMapping("/hey")
    public String hey() {
        return "hey World";
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute(CsrfToken.class.getName());
    }

}
