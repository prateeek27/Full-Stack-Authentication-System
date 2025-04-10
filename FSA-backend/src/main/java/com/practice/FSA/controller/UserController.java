package com.practice.FSA.controller;


import com.practice.FSA.entity.User;
import com.practice.FSA.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FSA/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public List<User> getUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/csrf")
    public CsrfToken getcsrf(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
    }
}
