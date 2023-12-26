package com.example.springdatabasicdemo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseDate {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name",nullable = false)
    private String firstname;

    @Column(name = "last_name",nullable = false)
    private String lastname;

    @Column(nullable = false)
    private Boolean isActive;


    @Column(name="image_url",nullable = false)
    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Offer> offer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable=false)
    private UserRole userRole;


    public User() {
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User(@NotEmpty(message = "User name cannot be null or empty!") @Size(min = 2, max = 20) String username, String encode, @NotEmpty(message = "Firstname name cannot be null or empty!") @Size(min = 5, max = 20) String firstname, @NotEmpty(message = "Lastname cannot be null or empty!") @Size(min = 5, max = 20) String lastname, String imageUrl) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Offer> getOffer() {
        return offer;
    }

    public void setOffer(List<Offer> offer) {
        this.offer = offer;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public User(String username, String password, String firstname, String lastname, Boolean isActive, String imageUrl) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", offer=" + offer +
                ", userRole=" + userRole +
                '}';
    }

}
