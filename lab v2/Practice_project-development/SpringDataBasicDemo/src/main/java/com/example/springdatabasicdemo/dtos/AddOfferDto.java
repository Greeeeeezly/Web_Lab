package com.example.springdatabasicdemo.dtos;


import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AddOfferDto {

    private String model;
    private String description;
    private Engine engine;

    private Transmission transmission;
    private String imageUrl;
    private int mileage;
    private Double price;
    private int year;


    @NotEmpty(message = "Name of model cannot be null or empty!")
    @Size(min = 2, message = "Name of model should be at least 2 characters long!")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 10, message = "Description must be at least 10 characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @NotNull(message = "Please choose an engine type!")
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 10, message = "Image URL must be minimum two characters!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotNull(message = "Mileage cannot be null or empty!")
    @Min(value = 1, message = "Mileage must be a positive number!")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price cannot be null or empty!")
    @Min(value = 1, message = "Price must be a positive number!")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @NotNull(message = "Choose transmission")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @NotNull(message = "Year cannot be null or empty!")
    @Min(value = 1, message = "Year must be a positive number!")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}