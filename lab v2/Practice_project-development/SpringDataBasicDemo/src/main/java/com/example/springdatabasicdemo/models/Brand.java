package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BaseDate {
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand",cascade = CascadeType.REMOVE)
    private List<Model> model;

    protected Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
