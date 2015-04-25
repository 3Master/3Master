package com.threemaster.entity;

import javax.persistence.Entity;

@Entity
public class User extends AbstractEntity {

    private String username;
    private String password;
    private String email;
    private String skill1;
    private String skill2;
    private String skill3;
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
    
}
