package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.util.ValidEndYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class ModelDto {

    private UUID id;
    @Size(min = 1, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String name;
    @NotNull
    private Category category;
    @NotBlank
    private String imageUrl ;
    @Min(1900)
    private Integer startYear ;
    @ValidEndYear
    private Integer endYear;

    private BrandDto brand;

    public ModelDto(){
    }


    public ModelDto(UUID id, String name, Category category, String imageUrl, Integer startYear, Integer endYear, BrandDto brand) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand=brand;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "ModelDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", brand=" + brand +
                '}';
    }
}
