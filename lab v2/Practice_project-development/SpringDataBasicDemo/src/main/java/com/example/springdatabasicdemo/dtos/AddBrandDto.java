package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.util.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AddBrandDto {
    @UniqueBrandName(message = "This brand is already exists")
    private String  name;

    public AddBrandDto(){}

    public AddBrandDto(String name){
        this.name = name;
    }
    @NotEmpty(message = "Brand name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Brand name must be between 2 and 10 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
