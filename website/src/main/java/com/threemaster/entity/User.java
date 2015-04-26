package com.threemaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    private String skill1;
    private String skill2;
    private String skill3;
    private String avatar;
    @Transient
    private boolean isApply = false;
    @Transient 
    private  boolean isTeacher = false;
    @Transient
    private Integer messageCount;
    
    public Integer getMessageCount() {
        return messageCount;
    }
    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }
    public boolean isApply() {
        return isApply;
    }
    public void setApply(boolean isApply) {
        this.isApply = isApply;
    }
    public boolean isTeacher() {
        return isTeacher;
    }
    public void setTeacher(boolean isTeacher) {
        this.isTeacher = isTeacher;
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
    
}
