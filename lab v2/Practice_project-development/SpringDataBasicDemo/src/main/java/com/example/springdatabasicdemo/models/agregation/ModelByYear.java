package com.example.springdatabasicdemo.models.agregation;

import java.util.UUID;

public class ModelByYear {
    private UUID id;
    private String name;
    private int year;

    public ModelByYear(UUID id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ModelByYear{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", year=" + year +
                '}';
    }
}
