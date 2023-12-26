package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.util.UniqueUsername;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;

@Validated
public class UserRegistrationDto {

    @UniqueUsername
    private String username;
    private String firstname;

    private String lastname;

    private String imageUrl;

    private String password;

    private String confirmPassword;

    public UserRegistrationDto() {}

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotEmpty(message = "Firstname name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    @NotEmpty(message = "Lastname cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
