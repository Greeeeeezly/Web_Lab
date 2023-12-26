package com.example.springdatabasicdemo.dtos;

import java.util.UUID;

public class ShowUserDto {
    private UUID id;
    private String username;

    public ShowUserDto(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public ShowUserDto() {
    }

    public ShowUserDto(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ShowUserDto{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
