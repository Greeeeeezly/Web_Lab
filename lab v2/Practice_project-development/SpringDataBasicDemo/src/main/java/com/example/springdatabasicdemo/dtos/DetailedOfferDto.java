package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;

import java.math.BigDecimal;
import java.util.UUID;

public class DetailedOfferDto {
    private UUID id;
    private String description;
    private Engine engine;

    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private Transmission transmission;
    private DetailedModelDto model;
    private DetailedUserDto user;

    public DetailedOfferDto(UUID id, String description, Engine engine, String imageUrl, Integer mileage, BigDecimal price, Transmission transmission, DetailedModelDto model, DetailedUserDto user) {
        this.id = id;
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.model = model;
        this.user = user;
    }

    public DetailedOfferDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public DetailedModelDto getModel() {
        return model;
    }

    public void setModel(DetailedModelDto model) {
        this.model = model;
    }

    public DetailedUserDto getUser() {
        return user;
    }

    public void setUser(DetailedUserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DetailedOfferDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", model=" + model +
                ", user=" + user +
                '}';
    }
}