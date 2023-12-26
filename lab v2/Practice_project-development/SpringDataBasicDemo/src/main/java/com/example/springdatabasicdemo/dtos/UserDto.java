package com.example.springdatabasicdemo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserDto {
/*
    @ValidUUID
*/
    private UUID id;
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String username;
    @NotBlank
    private String password;
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String firstname;
    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 20, message = "{validation.name.size.too_long}")
    private String lastname;
    private Boolean isActive;
    private String imageUrl;
    private UserRoleDto userRole;

    public UserDto(){
    }

    public UserDto(String username, String password, UserRoleDto userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }


    public UserDto(UUID id, String username, String password, String firstname, String lastname, String imageUrl, UserRoleDto userRole) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isActive = true;
        this.imageUrl = imageUrl;
        this.userRole = userRole;
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


    public UserRoleDto getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDto userRole) {
        this.userRole = userRole;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDto{" +
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
