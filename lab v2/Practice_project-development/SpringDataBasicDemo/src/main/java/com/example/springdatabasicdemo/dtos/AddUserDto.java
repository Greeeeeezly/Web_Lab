package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.models.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class AddUserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private UserRole roleName;


    public UserRole getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRole roleName) {
        this.roleName = roleName;
    }

    public AddUserDto() {
    }

    @NotEmpty(message = "Username cannot be null or empty!")
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
@NotEmpty(message = "First name cannot be null or empty!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
@NotEmpty(message = "Last name cannot be null or empty!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
@NotEmpty(message = "Image url cannot be null or empty!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}