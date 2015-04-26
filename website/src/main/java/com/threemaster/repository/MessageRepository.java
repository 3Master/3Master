package com.threemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.threemaster.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    
    List<Message> findByFromIdAndToIdAndRead(Integer fromId, Integer toId, boolean read);
    
    @Query("select m from Message m where (m.fromId=?1 or m.toId=?1) and read=false")
    List<Message> fingByUser(Integer fromId);
}
