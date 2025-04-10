package com.practice.FSA.service;

import com.practice.FSA.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    // This class will contain methods for user registration, login, and other user-related operations.
    // For now, we can leave it empty or add some basic methods.
    static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User("Prateek","123","pkb@gmail.com"));
        userList.add(new User("Amna","123","abc@gmail.com"));
    };



    public User registerUser(String username, String password, String email) {
        User user = new User(username, password, email);
        userList.add(user);
        return user;
    }

    public void loginUser(String username, String password) {
        // Logic for logging in a user
    }

    public void logoutUser() {
        // Logic for logging out a user
    }

    public void deleteUser(String username) {
        // Logic for deleting a user
    }

    public void updateUser(String username, String newPassword, String newEmail) {
        // Logic for updating user information
    }

    public void getUser(String username) {
        // Logic for retrieving user information
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void resetPassword(String username, String newPassword) {
        // Logic for resetting user password
    }
}
