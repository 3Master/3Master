package com.threemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threemaster.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);

}
