package com.example.springdatabasicdemo.dtos;


import com.example.springdatabasicdemo.enums.Category;

import java.util.UUID;

public class ShowModelDto {
    private UUID id;
    private String name;


    public ShowModelDto(String name) {
        this.name = name;
    }

    public ShowModelDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShowModelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
