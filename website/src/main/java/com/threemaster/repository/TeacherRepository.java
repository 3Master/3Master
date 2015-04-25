package com.threemaster.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.threemaster.entity.Teacher;
import com.threemaster.entity.User;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

    List<Teacher> findByTeacherAndActive(User user, boolean active);
    List<Teacher> findByStudentAndActive(User user, boolean active);

}
