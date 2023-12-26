package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Category;
import com.example.springdatabasicdemo.models.Brand;
import com.example.springdatabasicdemo.models.Offer;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

public class DetailedModelDto {
    private UUID id;
    private String name;

    private Category category;

    private String imageUrl ;

    private Integer startYear ;

    private Integer endYear ;

    private ShowBrandDto brand;

    public DetailedModelDto(UUID id, String name, Category category, String imageUrl, Integer startYear, Integer endYear, ShowBrandDto brand) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public DetailedModelDto() {
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public ShowBrandDto getBrand() {
        return brand;
    }

    public void setBrand(ShowBrandDto brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "DetailedModelDto{" +
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
