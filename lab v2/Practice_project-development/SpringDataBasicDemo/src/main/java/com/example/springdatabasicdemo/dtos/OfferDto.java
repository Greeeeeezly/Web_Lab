package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import com.example.springdatabasicdemo.models.User;
import com.example.springdatabasicdemo.util.ValidEndYear;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.UUID;

public class OfferDto {
    public OfferDto() {
    }

    /*
        @ValidUUID
    */
    private UUID id;
    @NotBlank
    private String description;
    private Engine engine;
    @NotBlank
    private String imageUrl;
    @Min(0)
    private Integer mileage;
    private BigDecimal price;
    private Transmission transmission;
    @ValidEndYear
    private Integer year;
    private ShowModelDto model;
    private UserDto seller;

    public OfferDto(UUID id, String description, Engine engine, String imageUrl, Integer mileage, BigDecimal price, Transmission transmission, Integer year, ShowModelDto model, UserDto seller) {
        this.id = id;
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model = model;
        this.seller = seller;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }


    public ShowModelDto getModel() {
        return model;
    }

    public void setModel(ShowModelDto model) {
        this.model = model;
    }

    public UserDto getSeller() {
        return seller;
    }

    public UserDto getUser() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", model=" + model +
                ", user=" + seller +
                '}';
    }
}
