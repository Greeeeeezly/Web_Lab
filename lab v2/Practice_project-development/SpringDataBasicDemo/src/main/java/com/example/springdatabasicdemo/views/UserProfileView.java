package com.example.springdatabasicdemo.views;

public class UserProfileView {
    private String username;

    private String firstname;

    private String lastname;

    private String imageUrl;

    public UserProfileView() {
    }

    public UserProfileView(String username, String firstname, String lastname, String imageUrl) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
