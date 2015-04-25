package com.threemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threemaster.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    
    List<Message> findByFromIdAndToIdAndRead(Integer fromId, Integer toId, boolean read);

}
