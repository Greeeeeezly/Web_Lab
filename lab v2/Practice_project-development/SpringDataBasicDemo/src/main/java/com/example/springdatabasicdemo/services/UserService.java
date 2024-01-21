package com.example.springdatabasicdemo.services;

import com.example.springdatabasicdemo.dtos.*;

import java.util.List;
import java.util.Optional;

public interface UserService<UUID>{

    UserDto register(UserDto user);
    void delete (UserDto user);
    void deleteById(UUID id);
    Optional<UserDto> getById(UUID id);
    Optional<DetailedUserDto> getByIdDetailed(UUID id);
    Optional<DetailedUserDto> getByNameDetailed(String userName);
    List<UserDto> getAll();
    AddUserDto register(AddUserDto user);
    List<ShowUserDto> getAllShow();
}
