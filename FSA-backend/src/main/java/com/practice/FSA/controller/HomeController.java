package com.practice.FSA.controller;


import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/FSA/api")
public class HomeController {

    @GetMapping("/hello")
    public HttpEntity<String> hello() {
        System.out.println("Hello from the FSA Application");
        return new HttpEntity<>("Welcome to the FSA Application");
    }

}
