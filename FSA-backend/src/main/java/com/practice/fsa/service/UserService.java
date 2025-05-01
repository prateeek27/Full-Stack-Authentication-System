package com.practice.fsa.service;

import com.practice.fsa.entity.User;
import com.practice.fsa.entity.UserPrincipal;
import com.practice.fsa.repository.UsersRepo;
import com.practice.fsa.util.JWTUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    public void saveInitailData() {
        usersRepo.save(new User("fas-dev", encoder.encode("123"), "fsa@gmail.com", "admin"));
        usersRepo.save(new User("Admin", encoder.encode("456"), "admin@gmail.com", "user"));
    }

    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepo.findByUsername(username);
        return optionalUser.map(UserPrincipal::new).orElse(null);
    }

    public String loginUser(String userName, String password) throws NoSuchAlgorithmException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            if (authentication.isAuthenticated()) {
                return JWTUtil.generateToken(userName);
            }
        } catch (Exception e) {
            return "Invalid User";
        }
        return "Invalid User";
    }
}
