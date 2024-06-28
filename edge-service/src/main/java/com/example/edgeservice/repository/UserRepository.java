package com.example.edgeservice.repository;

import com.example.edgeservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        User findByEmail(String email);

        Optional<User> findByToken(int token);

}
