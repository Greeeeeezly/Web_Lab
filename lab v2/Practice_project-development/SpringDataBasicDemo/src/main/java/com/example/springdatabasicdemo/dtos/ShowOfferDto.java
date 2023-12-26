package com.example.springdatabasicdemo.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class ShowOfferDto {
    private UUID id;
    private BigDecimal price;
    private Integer mileage;
    private int year;
    private DetailedModelDto model;
    private ShowUserDto user;

    public ShowOfferDto() {
    }

    public ShowOfferDto(UUID id, BigDecimal price, Integer mileage, int year, DetailedModelDto model, ShowUserDto user) {
        this.id = id;
        this.price = price;
        this.mileage = mileage;
        this.year = year;
        this.model = model;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DetailedModelDto getModel() {
        return model;
    }

    public void setModel(DetailedModelDto model) {
        this.model = model;
    }

    public ShowUserDto getUser() {
        return user;
    }

    public void setUser(ShowUserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShowOfferDto{" +
                "id=" + id +
                ", price=" + price +
                ", mileage=" + mileage +
                ", year=" + year +
                ", model=" + model +
                ", user=" + user +
                '}';
    }
}