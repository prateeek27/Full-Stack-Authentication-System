package com.practice.fsa.repository;

import com.practice.fsa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndEmail(String username, String email);
    User findByUsernameAndPassword(String username, String password);
}
