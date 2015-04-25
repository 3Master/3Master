package com.threemaster.entity;

import java.util.Date;

import javax.persistence.*;

import org.springframework.beans.BeanUtils;

@MappedSuperclass
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;

    public AbstractEntity() {
    }

    public AbstractEntity(AbstractEntity entity){
        BeanUtils.copyProperties(entity, this);
    }

    @PrePersist
    public void setCreatedTime() {
        this.createdTime = this.updatedTime = new Date();
    }

    @PreUpdate
    public void setUpdatedTime() {
        this.updatedTime = new Date();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public int hashCode() {
        if(id == null){
            return super.hashCode();
        }
        return this.id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null){
            return false;
        }
        if (!getClass().equals(obj.getClass()) ) {
            return false;
        }
        if(((AbstractEntity)obj).getId() == null || getId() == null){
            return false;
        }
        AbstractEntity abstractEntity = (AbstractEntity) obj;
        return getId().equals(abstractEntity.getId());
    }

}
