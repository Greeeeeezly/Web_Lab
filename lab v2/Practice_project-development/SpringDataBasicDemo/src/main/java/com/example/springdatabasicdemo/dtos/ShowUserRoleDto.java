package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Role;

import java.util.UUID;

public class ShowUserRoleDto {
    private UUID id;
    private Role role;

    public ShowUserRoleDto(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }


    public ShowUserRoleDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ShowUserRoleDto{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
