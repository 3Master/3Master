package com.threemaster.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threemaster.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
