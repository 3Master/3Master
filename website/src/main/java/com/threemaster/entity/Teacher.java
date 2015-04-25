package com.threemaster.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Teacher extends AbstractEntity {

    @ManyToOne
    private User teacher;
    @ManyToOne
    private User student;
    private boolean active;
    public User getTeacher() {
        return teacher;
    }
    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }
    public User getStudent() {
        return student;
    }
    public void setStudent(User student) {
        this.student = student;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
