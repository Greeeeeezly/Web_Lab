package com.example.springdatabasicdemo.controllers;

import com.example.springdatabasicdemo.dtos.UserRoleDto;
import com.example.springdatabasicdemo.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-roles")
public class UserRoleController {


    private UserRoleService<UUID> userRoleService;

    @Autowired
    public void setUserRoleService(UserRoleService<UUID> userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> createUserRole(@RequestBody UserRoleDto userRoleDto) {
        UserRoleDto newUserRole = userRoleService.register(userRoleDto);
        return ResponseEntity.ok(newUserRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserRole(@PathVariable UUID id) {
        userRoleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDto> getUserRole(@PathVariable UUID id) {
        return userRoleService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles() {
        List<UserRoleDto> userRoles = userRoleService.getAll();
        return ResponseEntity.ok(userRoles);
    }


}
