package com.example.springdatabasicdemo.dtos;

import com.example.springdatabasicdemo.enums.Role;

import java.util.UUID;

public class UserRoleDto {
/*
    @ValidUUID
*/
    private UUID id;
    private Role role;
    
    public UserRoleDto(){
    }

    public UserRoleDto(UUID id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRoleDto{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}
