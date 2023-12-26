package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.UserRoleDto;

import java.util.List;
import java.util.Optional;

public interface UserRoleService<UUID>{
    UserRoleDto register(UserRoleDto ur);
    void delete (UserRoleDto ur);
    void deleteById(UUID id);
    Optional<UserRoleDto> getById(UUID id);
    List<UserRoleDto> getAll();
}
