package com.practice.fsa.service;

import com.practice.fsa.entity.User;
import com.practice.fsa.entity.UserPrincipal;
import com.practice.fsa.repository.UsersRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;


    @PostConstruct
    public void saveInitailData() {
        usersRepo.save(new User("fas-dev", "123", "fsa@gmail.com"));
        usersRepo.save(new User("Admin", "123", "admin@gmail.com"));
    }

    public User registerUser(String username, String password, String email) {
        return usersRepo.save(new User(username, password, email));
    }

    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepo.findByUsername(username);
        return optionalUser.map(UserPrincipal::new).orElse(null);
    }
}
