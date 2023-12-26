package com.example.springdatabasicdemo.dtos;


import com.example.springdatabasicdemo.enums.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class AddModelDto {

    private String brand;
    private String name;
    private Category category;
    private String imageURL;
    private int startYear;
    private int endYear;


    @NotEmpty(message = "Model name cannot be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotEmpty(message = "Model name cannot be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
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

    @NotNull(message = "Please choose a category!")




    @NotEmpty(message = "Last name cannot be null or empty!")
    @Size(min = 5, message = "Image URL must be minimum two characters!")
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @NotNull(message = "Start year cannot be null or empty!")
    @Min(value = 1, message = "Start year must be real!")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @NotNull(message = "End year cannot be null or empty!")
    @Min(value = 1, message = "End year must be real!")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Override
    public String toString() {
        return "AddModelDto{" +
                "brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", imageURL='" + imageURL + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }

    //
//    @NotNull(message = "Date of model create cannot be null or empty!")
//    public LocalDate getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDate created) {
//        this.created = created;
//    }


}