package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.UUID;

public class DetailedBrandDto {
    private UUID id;
    private String name;
    private List<ShowModelDto> model;

    public DetailedBrandDto(UUID id, String name, List<ShowModelDto> model) {
        this.id = id;
        this.name = name;
        this.model = model;
    }

    public DetailedBrandDto() {
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

    public List<ShowModelDto> getModel() {
        return model;
    }

    public void setModel(List<ShowModelDto> model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "DetailedBrandDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model=" + model +
                '}';
    }
}
