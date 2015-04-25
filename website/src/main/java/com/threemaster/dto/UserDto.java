package com.threemaster.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.threemaster.entity.User;

public class UserDto {
    
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String skill1;
    private String skill2;
    private String skill3;
    private String avatar;
    private List<User> teacherList;
    private List<User> studentList;
    public UserDto() {
        super();
    }
    public UserDto(User user) {
        super();
        BeanUtils.copyProperties(user, this);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSkill1() {
        return skill1;
    }
    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }
    public String getSkill2() {
        return skill2;
    }
    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }
    public String getSkill3() {
        return skill3;
    }
    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public List<User> getTeacherList() {
        return teacherList;
    }
    public void setTeacherList(List<User> teacherList) {
        this.teacherList = teacherList;
    }
    public List<User> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<User> studentList) {
        this.studentList = studentList;
    }

}
