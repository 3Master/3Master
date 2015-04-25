package com.threemaster.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.threemaster.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findByUsername(String username);
    
    Page<User> findBySkill1OrSkill2OrSkill3(String skill1, String skill2, String skill3, Pageable pageable);

}
