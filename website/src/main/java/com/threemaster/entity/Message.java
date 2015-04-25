package com.threemaster.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Message extends AbstractEntity {
    
    private Integer fromId;
    private Integer toId;
    @Lob
    private String content;
    @Column(name="isRead")
    private boolean read;
    public Integer getFromId() {
        return fromId;
    }
    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }
    public Integer getToId() {
        return toId;
    }
    public void setToId(Integer toId) {
        this.toId = toId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public boolean isRead() {
        return read;
    }
    public void setRead(boolean read) {
        this.read = read;
    }

}
