package com.Demo.Security.Application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

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

}
