package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.Offer;
import com.example.springdatabasicdemo.models.UserRole;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

public class DetailedUserDto {
    private UUID id;

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private Boolean isActive;

    private String imageUrl;

    private ShowUserRoleDto userRole;

    public DetailedUserDto(UUID id, String username, String password, String firstname, String lastname, Boolean isActive, String imageUrl, ShowUserRoleDto userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.userRole = userRole;
    }

    public DetailedUserDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ShowUserRoleDto getUserRole() {
        return userRole;
    }

    public void setUserRole(ShowUserRoleDto userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "DetailedUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
