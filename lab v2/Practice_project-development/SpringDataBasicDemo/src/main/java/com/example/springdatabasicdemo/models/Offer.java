package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.Engine;
import com.example.springdatabasicdemo.enums.Transmission;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "offers")
public class Offer extends BaseDate{

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Engine engine;

    @Column(name="image_url",nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Transmission transmission;

    @Column(nullable = false)
    private Integer year;


    @ManyToOne(optional = false)
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable=false)
    private Model model;

    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable=false)
    private User user;

    public void setModel(Model model) {
        this.model = model;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected Offer() {
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

    public Model getModel() {
        return model;
    }

    public User getUser() {
        return user;
    }
}
