package com.example.springdatabasicdemo.dtos;

import jakarta.validation.constraints.Size;

import java.util.UUID;

public class BrandDto {

    private UUID id;
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String name;

    public BrandDto(){
    }

    public BrandDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BrandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
