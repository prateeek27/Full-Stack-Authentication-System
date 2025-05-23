package com.practice.fsa.controller;


import com.practice.fsa.entity.User;
import com.practice.fsa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/fsa/api/user")
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
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.loginUser(user.getUsername(),user.getPassword());
    }
}
