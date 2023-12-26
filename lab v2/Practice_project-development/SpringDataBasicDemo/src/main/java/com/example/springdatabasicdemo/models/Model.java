package com.example.springdatabasicdemo.models;

import com.example.springdatabasicdemo.enums.Category;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "models")
public class Model extends BaseDate {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Category category;

    @Column(name="image_url",nullable = false)
    private String imageUrl ;

    @Column(name="start_year",nullable = false)
    private Integer startYear ;

    @Column(name="end_year",nullable = false)
    private Integer endYear ;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "model",cascade = CascadeType.PERSIST)
    private List<Offer> offer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable=false)
    private Brand brand;

    protected Model() {
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

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Brand getBrand() {
        return brand;
    }
}
