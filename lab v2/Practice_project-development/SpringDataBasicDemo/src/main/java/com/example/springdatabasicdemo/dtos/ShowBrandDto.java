package com.example.springdatabasicdemo.dtos;

import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ShowBrandDto {
    private UUID id;

    private String name;

    public ShowBrandDto() {
    }

    public ShowBrandDto(UUID id, String name) {
        this.id = id;
        this.name = name;
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
        return "ShowBrandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
