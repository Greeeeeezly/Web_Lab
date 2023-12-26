package com.example.springdatabasicdemo.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;

@MappedSuperclass
public abstract class BaseDate extends BaseEntity {
    @Column(name = "created")
    @Temporal(TemporalType.DATE)
    Date created;

    @Column (name = "modified")
    @Temporal(TemporalType.DATE)
    Date modified;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
