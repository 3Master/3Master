package com.threemaster.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String skill1;
    @Column(nullable = false)
    private String skill2;
    @Column(nullable = false)
    private String skill3;
    private String avatar;
    
    @ManyToMany
    @JoinTable(name = "teachers", joinColumns = @JoinColumn(name = "teacherid", referencedColumnName = "id"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "teacherid", "studentid" }), inverseJoinColumns = @JoinColumn(name = "studentid", referencedColumnName = "id"))
    private List<User> teachers;
    
    @ManyToMany(mappedBy="teachers")
    private List<User> students;
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
